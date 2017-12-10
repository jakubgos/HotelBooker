package com.jgos.hotelBooker.reservation;

import android.os.Handler;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.serverEntity.endpoint.RateRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.UserReservationResponse;
import com.jgos.hotelBooker.reservation.interfaces.ReservationModelOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationPresenterOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationViewOps;
import com.jgos.hotelBooker.storage.Storage;

import java.lang.ref.WeakReference;

/**
 * Created by Bos on 2017-06-18.
 */

public class ReservationPresenter implements ReservationPresenterOps {
    private final WeakReference<ReservationViewOps> HotelDetailViewOps;
    private final ReservationModelOps reservationModelOps;
    private final Handler handler = new Handler();

    public ReservationPresenter(ReservationViewOps hotelListViewOps) {
        this.HotelDetailViewOps = new WeakReference<>(hotelListViewOps);
        this.reservationModelOps = new ReservationModel(this, new NetworkServiceImpl());
    }

    private ReservationViewOps getView() throws NullPointerException {
        if (HotelDetailViewOps != null)
            return HotelDetailViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup() {
        getView().showProgressBar();
        reservationModelOps.downloadUserReservation(Storage.getInstance().getLoginData());
    }

    @Override
    public void userReservationNotAvailable() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().hideListView();
                getView().stopProgressBar();
                getView().showDataNotAvailable();
            }
        });
    }

    @Override
    public void userReservationResult(final UserReservationResponse userReservationResponse) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().stopProgressBar();
                getView().initListView(userReservationResponse.getReservationDataArrayList());
            }
        });
    }

    @Override
    public void reservationRequestFailure(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showErrorDialogAndQuit(s);
            }
        });
    }

    @Override
    public void listItemSelect(ReservationData item) {

        if(item.isRated())
        {
            getView().makeToast("Nie możesz ponownie ocenić tego hotelu");

        }
        else {
            getView().showRateDialog(item);
        }
    }

    @Override
    public void showFilterRequested() {
        getView().showFilterActivity();
    }

    @Override
    public void rateConfirm(RateRequest rateRequest) {
        getView().showProgressBar();
        reservationModelOps.rateRequest(Storage.getInstance().getLoginData(), rateRequest);
    }

    @Override
    public void rateResultOk() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().stopProgressBar();
                getView().makeToast("Ocena zapisana");
                reservationModelOps.downloadUserReservation(Storage.getInstance().getLoginData());

            }
        });
    }

    @Override
    public void rateResultNok(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().stopProgressBar();
                getView().showErrorDialogAndQuit(s);
            }
        });
    }


}

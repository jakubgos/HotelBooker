package com.jgos.hotelBooker.hotelDetail;

import android.os.Handler;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationResponse;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailModelOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailPresenterOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailViewOps;
import com.jgos.hotelBooker.storage.Storage;

import java.lang.ref.WeakReference;

/**
 * Created by Bos on 2017-06-18.
 */

public class HotelDetailPresenter implements HotelDetailPresenterOps {
    private final WeakReference<HotelDetailViewOps> HotelDetailViewOps;
    private final HotelDetailModelOps hotelDetailModelOps;
    long reservationRoomId = -1;
    private final Handler handler = new Handler();


    public HotelDetailPresenter(HotelDetailViewOps hotelListViewOps) {
        this.HotelDetailViewOps = new WeakReference<>(hotelListViewOps);
        this.hotelDetailModelOps = new HotelDetailModel(this, new NetworkServiceImpl());
    }

    private HotelDetailViewOps getView() throws NullPointerException {
        if (HotelDetailViewOps != null)
            return HotelDetailViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup() {
        getView().prepareHotelData(Storage.getInstance().getSelectedHotelData().getHotelDetail());
        getView().prepareRoomData(Storage.getInstance().getSelectedHotelData().getRoomList().get(0));

        getView().showSnackBar();
    }

    @Override
    public void reservation(long roomId) {
        this.reservationRoomId = roomId;
        getView().showConfirmDialog();
    }

    @Override
    public void confirmReservation() {
        SearchRequest searchRequest = Storage.getInstance().getSelectedSearchRequest();
        if (reservationRoomId >= 0) {
            hotelDetailModelOps.reserveRoom(new ReservationRequest(searchRequest.getArrivalTime(), searchRequest.getDepartureTime(), reservationRoomId), Storage.getInstance().getLoginData());
            getView().showProgressDialog();
        } else {
            getView().showAlertDialog("Unexpected reservationRoomId: ," + reservationRoomId, true);
            Storage.getInstance().setUpdateListView(true);

        }

    }

    //From Activity
    @Override
    public void rejectReservation() {
        this.reservationRoomId = -1;
        getView().showSnackBar();
    }

    @Override
    public void reservationRequestReject(ReservationResponse reservationResponse) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().dismissProgressDialog();
                Storage.getInstance().setUpdateListView(true);
                getView().showAlertDialog("Nie możemy zrealizować twoiej rezerwacji, Wybierz inny termin.", true);
            }
        });
    }

    @Override
    public void reservationRequestResult(ReservationResponse reservationResponse) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().dismissProgressDialog();
                getView().showReservationSuccessDialog();
                Storage.getInstance().setUpdateListView(true);
            }
        });
    }

    @Override
    public void reservationRequestFailure(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().dismissProgressDialog();
                getView().showAlertDialog("Napotkaliśmy niespodziewany błąd: " + s, true);
                Storage.getInstance().setUpdateListView(true);

            }
        });
    }

    @Override
    public void ReservationSuccessDialogDisappear() {
        getView().endActivity();
        getView().showReservationActivity();
    }

    @Override
    public void showReservationRequested() {
        getView().showReservationActivity();
    }
}

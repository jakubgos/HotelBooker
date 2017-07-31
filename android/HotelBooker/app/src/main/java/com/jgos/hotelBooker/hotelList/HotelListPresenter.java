package com.jgos.hotelBooker.hotelList;

import android.os.Handler;
import android.util.Log;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;
import com.jgos.hotelBooker.hotelList.interfaces.HotelListModelOps;
import com.jgos.hotelBooker.hotelList.interfaces.HotelListPresenterOps;
import com.jgos.hotelBooker.hotelList.interfaces.HotelListViewOps;
import com.jgos.hotelBooker.storage.Storage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Bos on 2017-06-18.
 */

public class HotelListPresenter implements HotelListPresenterOps {
    private final WeakReference<HotelListViewOps> HotelListViewOps;
    private final HotelListModelOps hotelListModelOps;
    private final Handler handler = new Handler();
    private boolean updateOngoing = false;

    public HotelListPresenter(HotelListViewOps hotelListViewOps) {
        this.HotelListViewOps = new WeakReference<>(hotelListViewOps);
        this.hotelListModelOps = new HotelListModel(this, new NetworkServiceImpl());
    }

    private HotelListViewOps getView() throws NullPointerException {
        if (HotelListViewOps != null)
            return HotelListViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup() {
        getView().showProgressBar();
        hotelListModelOps.searchRequest(Storage.getInstance().getSelectedSearchRequest(), Storage.getInstance().getLoginData());
        //getView().initHotelListView((ArrayList) Storage.getInstance().getHotelOffer().getHotelData());
    }

    @Override
    public void listItemSelect(HotelData item) {
        Log.d("MyApp_HotelList", "onStartup invoked :" + item.toString());
        Storage.getInstance().setSelectedHotelData(item);
        getView().showHotelDetailView();
    }

    @Override
    public void getSearchRequestResult(final HotelOffer hotelOffer) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().stopProgressBar();
                Storage.getInstance().save(hotelOffer);
                if (updateOngoing) {
                    getView().updateListView(hotelOffer.getHotelData());
                } else {
                    getView().initHotelListView(hotelOffer.getHotelData());
                }
            }
        });
    }

    @Override
    public void getSearchRequestFailure(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().stopProgressBar();
                getView().showAlertDialogAndFinish(s);
            }
        });
    }

    @Override
    public void onResume() {

        if (Storage.getInstance().isUpdateListView()) {
            Log.d("MyApp_filter", "onResume invoked");

            getView().showProgressBar();
            Storage.getInstance().setUpdateListView(false);
            hotelListModelOps.searchRequest(Storage.getInstance().getSelectedSearchRequest(), Storage.getInstance().getLoginData());
            updateOngoing = true;
        }

    }

    @Override
    public void showReservationRequested() {
        getView().showReservationActivity();
    }
}

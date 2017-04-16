package com.jgos.hotelBooker.parkingList;

import android.os.Handler;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.entity.Parking;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingCallBackFromModel;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingListModel;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingListPresenter;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Bos on 2017-03-21.
 */

public class ParkingListPresenterImpl implements ParkingListPresenter, ParkingCallBackFromModel {

    private WeakReference<ParkingListView> view;
    private final ParkingListModel parkingListModel;
    private final Handler handler = new Handler();

    public ParkingListPresenterImpl(ParkingListView view) {
        this.view = new WeakReference<>(view);
        this.parkingListModel = new ParkingListModelImpl(new NetworkServiceImpl());
        //this.loginModelOps = new LoginModel(this, new NetworkServiceImpl());
    }

    private ParkingListView getView() throws NullPointerException {
        if ( view != null )
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartUp() {
        parkingListModel.getFavoriteParking(this);
    }

    @Override
    public void onFavoriteParkingResult(final ArrayList<Parking> parkingList) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showParkingList(parkingList);
            }
        });
    }
}

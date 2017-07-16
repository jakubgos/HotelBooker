package com.jgos.hotelBooker.hotelDetail;

import android.util.Log;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailViewOps;
import com.jgos.hotelBooker.storage.Storage;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailModelOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailPresenterOps;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Bos on 2017-06-18.
 */

public class HotelDetailPresenter implements HotelDetailPresenterOps {
    private final WeakReference<HotelDetailViewOps> HotelDetailViewOps;
    private final HotelDetailModelOps hotelDetailModelOps;

    public HotelDetailPresenter(HotelDetailViewOps hotelListViewOps) {
        this.HotelDetailViewOps =new WeakReference<>(hotelListViewOps);
        this.hotelDetailModelOps = new HotelDetailModel(this, new NetworkServiceImpl());
    }

    private HotelDetailViewOps getView() throws NullPointerException {
        if ( HotelDetailViewOps != null )
            return HotelDetailViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }
    @Override
    public void onStartup() {
        getView().prepareHotelData(Storage.getInstance().getSelectedHotelData().getHotelDetail());
    }
}

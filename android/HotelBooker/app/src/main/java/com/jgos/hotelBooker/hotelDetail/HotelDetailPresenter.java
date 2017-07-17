package com.jgos.hotelBooker.hotelDetail;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
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
    }
}

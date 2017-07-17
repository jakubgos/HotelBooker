package com.jgos.hotelBooker.reservation;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.reservation.interfaces.ReservationModelOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationPresenterOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationViewOps;

import java.lang.ref.WeakReference;

/**
 * Created by Bos on 2017-06-18.
 */

public class ReservationPresenter implements ReservationPresenterOps {
    private final WeakReference<ReservationViewOps> HotelDetailViewOps;
    private final ReservationModelOps hotelDetailModelOps;

    public ReservationPresenter(ReservationViewOps hotelListViewOps) {
        this.HotelDetailViewOps = new WeakReference<>(hotelListViewOps);
        this.hotelDetailModelOps = new ReservationModel(this, new NetworkServiceImpl());
    }

    private ReservationViewOps getView() throws NullPointerException {
        if (HotelDetailViewOps != null)
            return HotelDetailViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup() {

    }
}

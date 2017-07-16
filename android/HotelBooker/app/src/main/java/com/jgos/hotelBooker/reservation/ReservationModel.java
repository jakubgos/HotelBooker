package com.jgos.hotelBooker.reservation;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.reservation.interfaces.ReservationModelOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationPresenterOps;

/**
 * Created by Bos on 2017-06-18.
 */

class ReservationModel implements ReservationModelOps {
    private final ReservationPresenterOps reservationPresenter;

    //todo remove:, replace with callback
    private final NetworkServiceImpl networkService;

    public ReservationModel(ReservationPresenterOps ReservationPresenterOps, NetworkServiceImpl networkService) {
        this.reservationPresenter = ReservationPresenterOps;
        this.networkService=networkService;
    }
}

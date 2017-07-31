package com.jgos.hotelBooker.reservation;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.data.serverEntity.endpoint.UserReservationResponse;
import com.jgos.hotelBooker.reservation.interfaces.ReservationModelOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationPresenterOps;
import com.jgos.hotelBooker.reservation.interfaces.UserReservationResult;

/**
 * Created by Bos on 2017-06-18.
 */

class ReservationModel implements ReservationModelOps {
    private final ReservationPresenterOps reservationPresenter;

    //todo remove:, replace with callback
    private final NetworkService networkService;

    public ReservationModel(ReservationPresenterOps ReservationPresenterOps, NetworkServiceImpl networkService) {
        this.reservationPresenter = ReservationPresenterOps;
        this.networkService = networkService;
    }

    @Override
    public void downloadUserReservation(LoginData loginData) {
        networkService.downloadUserReservation(loginData, new UserReservationResult(){
            @Override
            public void userReservationNotAvailable() {
                reservationPresenter.userReservationNotAvailable();
            }

            @Override
            public void userReservationResult(UserReservationResponse userReservationResponse) {
                reservationPresenter.userReservationResult(userReservationResponse);
            }

            @Override
            public void reservationRequestFailure(String s) {
                reservationPresenter.reservationRequestFailure(s);
            }
        });
    }
}

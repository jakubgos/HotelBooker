package com.jgos.hotelBooker.reservation.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.RateRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.UserReservationResponse;

/**
 * Created by Bos on 2017-06-18.
 */

public interface ReservationPresenterOps {
    void onStartup();

    void userReservationNotAvailable();

    void userReservationResult(UserReservationResponse userReservationResponse);

    void reservationRequestFailure(String s);

    void listItemSelect(ReservationData item);

    void showFilterRequested();

    void rateConfirm(RateRequest rateRequest);

    void rateResultOk();

    void rateResultNok(String s);
}

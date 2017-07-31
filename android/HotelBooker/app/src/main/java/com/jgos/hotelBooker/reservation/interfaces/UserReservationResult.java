package com.jgos.hotelBooker.reservation.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.UserReservationResponse;

/**
 * Created by Bos on 2017-07-25.
 */

public interface UserReservationResult {

    void userReservationNotAvailable();

    void userReservationResult(UserReservationResponse userReservationResponse);

    void reservationRequestFailure(String s);
}

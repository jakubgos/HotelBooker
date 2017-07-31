package com.jgos.hotelBooker.reservation.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;

/**
 * Created by Bos on 2017-06-18.
 */

public interface ReservationModelOps {
    void downloadUserReservation(LoginData loginData);
}

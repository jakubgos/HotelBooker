package com.jgos.hotelBooker.hotelDetail.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationRequest;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelDetailModelOps {
    void reserveRoom(ReservationRequest reservationRequest, LoginData loginData);
}

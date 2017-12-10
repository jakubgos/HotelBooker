package com.jgos.hotelBooker.reservation.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.UserReservationResponse;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.ResultStatus;

/**
 * Created by Bos on 2017-07-25.
 */

public interface RateRequestResult {
    void rateResultOk();
    void rateResultNok(String s);
}

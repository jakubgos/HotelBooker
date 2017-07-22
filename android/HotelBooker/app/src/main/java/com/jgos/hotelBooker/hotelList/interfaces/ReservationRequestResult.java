package com.jgos.hotelBooker.hotelList.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationResponse;

/**
 * Created by Bos on 2017-07-22.
 */

public interface ReservationRequestResult {
    void reservationRequestFailure(String s);

    void reservationRequestResult(ReservationResponse reservationResponse);

    void reservationRequestReject(ReservationResponse reservationResponse);
}

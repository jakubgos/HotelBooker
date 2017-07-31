package com.jgos.hotelBooker.hotelDetail.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationResponse;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelDetailPresenterOps {
    void onStartup();

    void reservation(long roomId);

    void confirmReservation();

    void rejectReservation();

    void reservationRequestReject(ReservationResponse reservationResponse);

    void reservationRequestResult(ReservationResponse reservationRequest);

    void reservationRequestFailure(String s);

    void ReservationSuccessDialogDisappear();

    void showReservationRequested();
}

package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.ReservationRequest;
import com.jgos.hotelbooker.entity.endpoint.ReservationResponse;
import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;

import java.util.List;

/**
 * Created by Bos on 2017-07-23.
 */
public interface ReservationService {
    boolean validate(ReservationRequest reservationRequest);

    ReservationResponse reserve(ReservationRequest reservationRequest, String username);

    UserReservationResponse getUserReservation(String username);

    List<Reservation> getReservationFromStatus(String username, ReservationStatus waitForConfirmation);
}

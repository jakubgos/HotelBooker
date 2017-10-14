package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.user.Reservation;

import java.util.List;

/**
 * Created by Bos on 2017-10-14.
 */

public interface ReservationParserService {
    UserReservationResponse parseReservation(List<Reservation> reservationList, UserReservationResponse ok);
}

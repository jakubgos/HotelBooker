package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.ReservationData;
import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.user.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-10-14.
 */

public interface ReservationParserService {
    List<ReservationData> parseReservation(List<Reservation> reservationList);

    UserReservationResponse parseReservation(List<Reservation> reservationList, UserReservationResponse userReservationResponse);

    List<Reservation> addToDate(ArrayList<Reservation> reservationFromStatus);
}

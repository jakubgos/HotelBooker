package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.ReservationRequest;
import com.jgos.hotelbooker.entity.endpoint.ReservationResponse;

/**
 * Created by Bos on 2017-07-23.
 */
public interface ReservationService {
    boolean validate(ReservationRequest reservationRequest);

    ReservationResponse reserve(ReservationRequest reservationRequest, String username);
}

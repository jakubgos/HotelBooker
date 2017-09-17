package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Bos on 2017-05-28.
 */

@Controller
@RequestMapping("/test")
public class TestC {
    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @Autowired
    UserRepository users;

    @Autowired
    HotelRepository hotels;

    @Autowired
    ReservationRepository reservationRepository;

}

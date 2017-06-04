package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.City;
import com.jgos.hotelbooker.entity.Hotel;
import com.jgos.hotelbooker.entity.Reservation;
import com.jgos.hotelbooker.entity.UserDb;
import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String searchOffer(
    //        @RequestBody City city
    )
            throws InterruptedException {


        UserDb user = users.findOne((long) 1);
        Hotel hotel = hotels.findOne((long)1);

        Reservation reservation = new Reservation(hotel.getRoomList().get(0),user,new Date());

        log.info(user.toString());
        log.info(hotel.toString());
        log.info(reservation.toString());
        reservationRepository.save(reservation);

        Reservation newRes = reservationRepository.findOne((long) 1);



        return user+"\n "+hotel +"\nOLD::" +reservation + "\n NEW:: " + newRes;
    }

}

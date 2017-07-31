package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.endpoint.*;
import com.jgos.hotelbooker.entity.hotel.data.City;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;
import com.jgos.hotelbooker.repository.CityRepository;
import com.jgos.hotelbooker.service.OfferSearch;
import com.jgos.hotelbooker.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Bos on 2017-04-16.
 */


@RestController
@RequestMapping("/api")
public class ApiHotel {

    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private OfferSearch offerSearch;

    @Autowired
    private ReservationService reservationService;


    @RequestMapping("/test")
    @ResponseBody
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        return "OK!";
    }

    @RequestMapping("/getCityList")
    public List<City> getCityList(Model model) throws InterruptedException {
        return (List<City>) cityRepository.findAll();

    }
    //  public SearchRequest(City city, long arrivalTime, long departureTime, int numberOfPeople) {

    @RequestMapping(value = "/searchOffer", method = RequestMethod.POST)
    @ResponseBody
    public HotelOffer searchOffer(
            @Valid @RequestBody SearchRequest searchRequest) throws InterruptedException {
        log.info("searchOffer received with data:" + searchRequest);

        HotelOffer hotelOffer = offerSearch.search(searchRequest);
        log.info(hotelOffer.toString());
        return hotelOffer;
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    @ResponseBody
    public ReservationResponse reservation(@AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody ReservationRequest reservationRequest) throws InterruptedException {
        ReservationResponse reservationResponse;
        if (reservationService.validate(reservationRequest)) {
            reservationResponse = reservationService.reserve(reservationRequest, userDetails.getUsername());
        } else {
            reservationResponse = new ReservationResponse(ResultStatus.RESERVATION_NOT_POSSIBLE);

        }

        log.info(reservationResponse.toString());
        return reservationResponse;
    }

    @RequestMapping(value = "/getUserReservation")
    @ResponseBody
    public UserReservationResponse getUserReservation(@AuthenticationPrincipal UserDetails userDetails ) throws InterruptedException {

        UserReservationResponse userReservationResponse = reservationService.getUserReservation(userDetails.getUsername());


        log.info(userReservationResponse.toString());
        return userReservationResponse;
    }



}

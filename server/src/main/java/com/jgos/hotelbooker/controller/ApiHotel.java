package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.City;
import com.jgos.hotelbooker.entity.HotelOffer;
import com.jgos.hotelbooker.entity.SearchRequest;
import com.jgos.hotelbooker.repository.CityRepository;
import com.jgos.hotelbooker.service.HotelSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private HotelSearch hotelSearch;

    @RequestMapping("/test")
    @ResponseBody
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
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

        HotelOffer ho =hotelSearch.search(searchRequest);
        return ho;
    }


}

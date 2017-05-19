package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.City;
import com.jgos.hotelbooker.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bos on 2017-04-16.
 */


@RestController
@RequestMapping("/api")
public class ApiHotel {


    @Autowired
    private CityRepository cityRepository;


    @RequestMapping("/test")
    @ResponseBody
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        return "OK!";
    }

    @RequestMapping("/getCityList")
    public List<City> getCityList(Model model) throws InterruptedException {
        return (List<City>) cityRepository.findAll();

    }


}

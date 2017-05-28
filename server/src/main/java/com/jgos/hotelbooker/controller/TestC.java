package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Bos on 2017-05-28.
 */

@Controller
@RequestMapping("/test")
public class TestC {
    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String searchOffer(
            @RequestBody City city)
            throws InterruptedException {
        log.info("searchOffer recieved with data: city:" + city);
        return "ok";
    }

}

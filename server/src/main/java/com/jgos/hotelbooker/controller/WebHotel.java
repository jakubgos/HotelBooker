package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.repository.*;
import com.jgos.hotelbooker.service.OfferSearch;
import com.jgos.hotelbooker.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Bos on 2017-04-16.
 */


@RestController
public class WebHotel {


    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private OfferSearch offerSearch;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodOfferRepository foodOfferRepository;

    @Autowired
    HotelFacilitiesRepository hotelFacilitiesRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    HotelDetailRepository hotelDetailRepository;

    @RequestMapping("/test")
    @ResponseBody
    public String greeting(@AuthenticationPrincipal UserDetails userDetails) {
        return "ok";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage (RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        if (auth != null){
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            model.setViewName("redirect:/login");
            redirectAttributes.addFlashAttribute("logout",1);

        }
        return model;
    }

    @RequestMapping(value={"/index","/"}, method = RequestMethod.GET)
    public ModelAndView home (@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("noOfwaitingReservation", reservationService.getReservationFromStatus(userDetails.getUsername(), ReservationStatus.WAIT_FOR_CONFIRMATION).size());
        model.addObject("userName", userDetails.getUsername());
        return model;
    }

    @RequestMapping(value={"/hotelData"}, method = RequestMethod.GET)
    public ModelAndView hotelData (@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required=false, defaultValue = "0") int result) {
        ModelAndView model = new ModelAndView();
        UserDb user = userRepository.findByEmail(userDetails.getUsername());

        model.setViewName("hotelData");
        model.addObject("hotelDetail",hotelRepository.findByOwner(user).getHotelDetail());
        model.addObject("cityList",cityRepository.findAll());
        model.addObject("allFoodOffer",foodOfferRepository.findAll());
        model.addObject("allHotelFacilities",hotelFacilitiesRepository.findAll());
        model.addObject("allRating",ratingRepository.findAll());
        if(result == 1) {
            model.addObject("message","Zmiany zostały zapisane");
        }
        else if (result == 2)
        {
            model.addObject("errorMessage","Wystąpił Błąd. Nie udało się zapisać danych");

        }
        return model;
    }

    @RequestMapping(value={"/saveHotelData"}, method = RequestMethod.POST)
    public ModelAndView saveHotelData (@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("HotelData") HotelDetail hotelDetail, BindingResult errors, Model model) {

        UserDb user = userRepository.findByEmail(userDetails.getUsername());

        Hotel hotel = hotelRepository.findByOwner(user);

        ModelAndView newModel = new ModelAndView();
        newModel.setViewName("");

        if(hotel.getHotelDetail().getId() != hotelDetail.getId())
        {
            log.error("saveHotelData: id missmatch!");
            return new ModelAndView("redirect:/hotelData?result=2");
        }

        hotel.setHotelDetail(hotelDetail);
        hotelDetailRepository.save(hotelDetail);
        return new ModelAndView("redirect:/hotelData?result=1");
    }

}

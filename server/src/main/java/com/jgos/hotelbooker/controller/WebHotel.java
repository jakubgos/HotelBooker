package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.repository.CityRepository;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Bos on 2017-04-16.
 */


@RestController
@RequestMapping("/web")
public class WebHotel {


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


    @RequestMapping(value="/try", method = RequestMethod.GET)
    @ResponseBody
    public String home(@AuthenticationPrincipal UserDetails userDetails){

      /*  ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDeta user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");

        */
        return userDetails.toString();
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

}

package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.entity.webData.WrapperReservation;
import com.jgos.hotelbooker.entity.webData.WrapperReservationData;
import com.jgos.hotelbooker.repository.*;
import com.jgos.hotelbooker.service.OfferSearch;
import com.jgos.hotelbooker.service.ReservationService;
import com.jgos.hotelbooker.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import static com.jgos.hotelbooker.entity.user.ReservationStatus.getEnumByString;

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
    private
    HotelRepository hotelRepository;

    @Autowired
    private
    RoomRepository roomRepository;

    @Autowired
    private
    UserRepository userRepository;

    @Autowired
    private
    FoodOfferRepository foodOfferRepository;

    @Autowired
    private
    HotelFacilitiesRepository hotelFacilitiesRepository;

    @Autowired
    private
    RatingRepository ratingRepository;

    @Autowired
    private HotelDetailRepository hotelDetailRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomFacilitiesRepository roomFacilitiesRepository;

    @Autowired
    private RoomService roomService;

    @RequestMapping("/test")
    @ResponseBody
    public String greeting(@AuthenticationPrincipal UserDetails userDetails) {
        return "ok";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutPage(RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        if (auth != null) {
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            model.setViewName("redirect:/login");
            redirectAttributes.addFlashAttribute("logout", 1);

        }
        return model;
    }

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public ModelAndView home(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("noOfwaitingReservation", reservationService.getReservationFromStatus(userDetails.getUsername(), ReservationStatus.WAIT_FOR_CONFIRMATION).size());
        model.addObject("userName", userDetails.getUsername());
        return model;
    }

    @RequestMapping(value = {"/hotelData"}, method = RequestMethod.GET)
    public ModelAndView hotelData(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, defaultValue = "0") int result) {
        ModelAndView model = new ModelAndView();
        UserDb user = userRepository.findByEmail(userDetails.getUsername());

        model.setViewName("hotelData");
        model.addObject("hotelDetail", hotelRepository.findByOwner(user).getHotelDetail());
        model.addObject("cityList", cityRepository.findAll());
        model.addObject("allFoodOffer", foodOfferRepository.findAll());
        model.addObject("allHotelFacilities", hotelFacilitiesRepository.findAll());
        model.addObject("allRating", ratingRepository.findAll());
        if (result == 1) {
            model.addObject("message", "Zmiany zostały zapisane");
        } else if (result == 2) {
            model.addObject("errorMessage", "Wystąpił Błąd. Nie udało się zapisać danych");

        }
        return model;
    }

    @RequestMapping(value = {"/saveHotelData"}, method = RequestMethod.POST)
    public ModelAndView saveHotelData(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("HotelData") HotelDetail hotelDetail, BindingResult errors, Model model) {

        UserDb user = userRepository.findByEmail(userDetails.getUsername());

        Hotel hotel = hotelRepository.findByOwner(user);

        ModelAndView newModel = new ModelAndView();
        newModel.setViewName("");

        if (hotel.getHotelDetail().getId() != hotelDetail.getId()) {
            log.error("saveHotelData: id missmatch!");
            return new ModelAndView("redirect:/hotelData?result=2");
        }

        hotel.setHotelDetail(hotelDetail);
        hotelDetailRepository.save(hotelDetail);
        return new ModelAndView("redirect:/hotelData?result=1");
    }

    @RequestMapping(value = {"/wRes"}, method = RequestMethod.GET)
    public ModelAndView wres(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, defaultValue = "0") int result) {

        ModelAndView model = new ModelAndView();
        model.setViewName("wRes");
        model.addObject("reservationsWrapper", new WrapperReservationData(reservationService.getReservationFromStatus(userDetails.getUsername(), ReservationStatus.WAIT_FOR_CONFIRMATION)));
        model.addObject("userName", userDetails.getUsername());

        ArrayList<ReservationStatus> reservationStatuses = new ArrayList<>();
        for (int i = 0; i < ReservationStatus.UNKNOWN.ordinal(); i++) {
            reservationStatuses.add(ReservationStatus.values()[i]);
        }
        model.addObject("reservationOptionAll", reservationStatuses);
        model.addObject("cityList", cityRepository.findAll());
        if (result == 1) {
            model.addObject("message", "Zmiany zostały zapisane");
        } else if (result == 2) {
            model.addObject("message", "Nie wybrano żadnych pozycji");

        }
        return model;
    }

    @RequestMapping(value = {"/rRes"}, method = RequestMethod.GET)
    public ModelAndView rres(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, defaultValue = "0") int result) {
        ModelAndView model = new ModelAndView();
        model.setViewName("rRes");
        model.addObject("reservationsWrapper", new WrapperReservationData(reservationService.getReservation(userDetails.getUsername())));
        model.addObject("userName", userDetails.getUsername());

        ArrayList<ReservationStatus> reservationStatuses = new ArrayList<>();
        for (int i = 0; i < ReservationStatus.UNKNOWN.ordinal(); i++) {
            reservationStatuses.add(ReservationStatus.values()[i]);
        }
        model.addObject("reservationOptionAll", reservationStatuses);
        model.addObject("cityList", cityRepository.findAll());
        if (result == 1) {
            model.addObject("message", "Zmiany zostały zapisane");
        } else if (result == 2) {
            model.addObject("message", "Nie wybrano żadnych pozycji");

        }
        return model;
    }

    @RequestMapping(value = {"/rResAction"}, method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView rResAction(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("reservationsWrapper") WrapperReservationData wrapperReservationData, BindingResult errors, Model model) {
        log.info("rResAction initaited with data : " + wrapperReservationData.toString());
        int result = 2;
        for (WrapperReservation res : wrapperReservationData.getReservations()
                ) {
            if (res.isSelected()) {
                Reservation reservation = reservationRepository.findOne(res.getId());
                reservation.setReservationStatus(getEnumByString(wrapperReservationData.getReservationStatus().getText()));
                reservationRepository.save(reservation);
                result = 1;
            }
        }
        return new ModelAndView("redirect:/rRes?result=" + result);
    }

    @RequestMapping(value = {"/wResAction"}, method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView wResAction(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("reservationsWrapper") WrapperReservationData wrapperReservationData, BindingResult errors, Model model) {
        log.info("wResAction initaited with data : " + wrapperReservationData.toString());
        int result = 2;
        for (WrapperReservation res : wrapperReservationData.getReservations()
                ) {
            if (res.isSelected()) {
                Reservation reservation = reservationRepository.findOne(res.getId());

                reservation.setReservationStatus(getEnumByString(wrapperReservationData.getReservationStatus().getText()));
                reservationRepository.save(reservation);
                result = 1;
            }
        }
        return new ModelAndView("redirect:/wRes?result=" + result);
    }

    @RequestMapping(value = {"/room"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView room(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, defaultValue = "0") int result) {
        UserDb user = userRepository.findByEmail(userDetails.getUsername());
        Hotel hotel = hotelRepository.findByOwner(user);

        ModelAndView model = new ModelAndView();
        model.setViewName("room");
        model.addObject("roomList", hotel.getRoomList());
        model.addObject("userName", userDetails.getUsername());

        if (result == 2) {
            model.addObject("errorMessage", "Nie udało się zapisać danych pokoju.");
        }

        if (result == 1) {
            model.addObject("message", "Pokój został zapisany");
        }

        return model;
    }

    @RequestMapping(value = {"/editRoom"}, method = RequestMethod.POST)
    public ModelAndView editRoom(@AuthenticationPrincipal UserDetails userDetails, @RequestParam() int roomId) {
        ModelAndView model = new ModelAndView();
        model.setViewName("editRoom");
        model.addObject("room", roomRepository.findById(roomId));
        model.addObject("allRoomFacilities", roomFacilitiesRepository.findAll());

        return model;
    }

    @RequestMapping(value = {"/saveRoomData"}, method = RequestMethod.POST)
    public ModelAndView saveRoomData(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("editRoom") Room room, BindingResult errors, Model model) {

        log.info("saveRoomData recieved with " + room.toString());
        UserDb user = userRepository.findByEmail(userDetails.getUsername());

        if (!roomService.verifyData(room)) {
            return new ModelAndView("redirect:/room?result=2");
        }
        Hotel hotel = hotelRepository.findByOwner(user);
        room.setHotel(hotel);
        roomRepository.save(room);
        return new ModelAndView("redirect:/room?result=1");
    }
}

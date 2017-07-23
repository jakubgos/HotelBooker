package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.controller.ApiHotel;
import com.jgos.hotelbooker.entity.endpoint.ReservationRequest;
import com.jgos.hotelbooker.entity.endpoint.ReservationResponse;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.RoomRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-07-23.
 */

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    HotelRepository hotels;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;


    @Override
    public boolean validate(ReservationRequest reservationRequest) {
        Date from = new Date(reservationRequest.getArrivalTime());
        Date to = new Date(reservationRequest.getDepartureTim());

        Calendar cal = Calendar.getInstance();
        cal.setTime(to);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        to=cal.getTime();

        List<Reservation> restrictedReservations = reservationRepository.findByDateBetweenAndRoomId(from, to,reservationRequest.getReservationRoomId());
        log.info(restrictedReservations.toString());

        return restrictedReservations.isEmpty();
    }

    @Override
    public ReservationResponse reserve(ReservationRequest reservationRequest, String username) {

        Date from = new Date(reservationRequest.getArrivalTime());
        Date to = new Date(reservationRequest.getDepartureTim());


        Calendar start = Calendar.getInstance();
        start.setTime(from);
        Calendar end = Calendar.getInstance();
        end.setTime(to);

        UserDb user = userRepository.findByEmail(username);
        Room room = roomRepository.findById(reservationRequest.getReservationRoomId());
        //end.add(Calendar.DAY_OF_MONTH, -1); for nie łapie ostatniego dnia i tak :)
        log.info("asdasd");
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            Reservation reservation = new Reservation(room, user, date);
            reservationRepository.save(reservation);
        }
        return new ReservationResponse(ResultStatus.OK);
    }
}

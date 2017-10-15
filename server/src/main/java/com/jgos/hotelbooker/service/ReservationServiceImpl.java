package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.controller.ApiHotel;
import com.jgos.hotelbooker.entity.endpoint.ReservationRequest;
import com.jgos.hotelbooker.entity.endpoint.ReservationResponse;
import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.RoomRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    ReservationParserService reservationParserService;


    @Override
    public boolean validate(ReservationRequest reservationRequest) {
        log.info("reservation service 'validate' started with data:" + reservationRequest);

        Date from = new Date(reservationRequest.getArrivalTime());
        Date to = new Date(reservationRequest.getDepartureTim());

        Calendar cal = Calendar.getInstance();
        cal.setTime(to);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        to = cal.getTime();

        for (Reservation reservation: reservationRepository.findBetweenDates(from,to))
        {
            if(reservation.getRoom().getId()==reservationRequest.getReservationRoomId())return  false;
        }

        return true;

    }

    @Override
    public ReservationResponse reserve(ReservationRequest reservationRequest, String username) {
        log.info("reservation service 'reserve' started with data:" + reservationRequest + "user " + username);

        Date from = new Date(reservationRequest.getArrivalTime());
        Date to = new Date(reservationRequest.getDepartureTim());


        Calendar end = Calendar.getInstance();
        end.setTime(to);
        end.add(Calendar.DAY_OF_YEAR,-1);

        UserDb user = userRepository.findByEmail(username);
        Room room = roomRepository.findById(reservationRequest.getReservationRoomId());
        //end.add(Calendar.DAY_OF_MONTH, -1); for nie łapie ostatniego dnia i tak :)


            Reservation reservation = new Reservation(
                    user,
                    from,
                    end.getTime(),
                    room,
                    room
                            .getHotel()
                            .getOwner(),
                    ReservationStatus.WAIT_FOR_CONFIRMATION);

        reservationRepository.save(reservation);

        return new ReservationResponse(ResultStatus.OK);
    }

    @Override
    public UserReservationResponse getUserReservation(String username) {

        UserReservationResponse userReservationResponse = new UserReservationResponse(ResultStatus.NO_DATA);

        List<Reservation> reservationList = reservationRepository.findByUserEmail(username);

        if (!reservationList.isEmpty()) {
            userReservationResponse.setStatus(ResultStatus.OK);

            userReservationResponse = reservationParserService.parseReservation(reservationList,userReservationResponse);
        }
        return userReservationResponse;
    }

    @Override
    public ArrayList<Reservation> getReservationFromStatus(String hotelUserName, ReservationStatus reservationStatus) {
        UserDb hotelUser = userRepository.findByEmail(hotelUserName);

        return reservationRepository.findByOwnerAndReservationStatus(hotelUser, reservationStatus);
    }

    @Override
    public ArrayList<Reservation> getReservation(String username) {
        return reservationRepository.findByOwnerEmail(username);
    }
}

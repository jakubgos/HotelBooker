package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.controller.ApiHotel;
import com.jgos.hotelbooker.entity.*;
import com.jgos.hotelbooker.entity.shared.*;
import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.jgos.hotelbooker.repository.RoomRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Bos on 2017-05-28.
 */

@Service
public class OfferSearchImpl implements OfferSearch {
    @Autowired
    UserRepository users;

    @Autowired
    HotelRepository hotels;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;
    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);


    @Override
    public HotelOffer search(SearchRequest searchRequest) {

        Date from = new Date(searchRequest.getArrivalTime());
        Date to = new Date(searchRequest.getDepartureTime());

        List<Reservation> restrictedReservations = reservationRepository.findByDateBetween(from,to);
        Collection<Long> restrictedRooms = new LinkedHashSet<Long>();
        for (Reservation reservation: restrictedReservations) {
            restrictedRooms.add(reservation.getRoom().getId());
        }
        List<Hotel> hotelList = hotelRepository.findByHotelDetailCity(searchRequest.getCity());

        HotelOffer hotelOffer = new HotelOffer(HotelResultStatus.NO_DATA);
        for (Hotel hotel: hotelList) {
            List<Room> roomList = new ArrayList<>();
            for (Room room: hotel.getRoomList()) {
                if(!restrictedRooms.contains(room.getId()) && room.getSize() == searchRequest.getNumberOfPeople())
                {
                    roomList.add(room);
                }
            }
            if(!roomList.isEmpty())
            {
                hotelOffer.addHotelData(new HotelData(hotel.getHotelDetail(),roomList));
                hotelOffer.setStatus(HotelResultStatus.OK);
            }
        }
        return hotelOffer;
    }
}
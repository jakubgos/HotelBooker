package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.ReservationData;
import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Bos on 2017-10-14.
 */
@Service
public class ReservationParserServiceImpl implements ReservationParserService{

    private static final Logger log = LoggerFactory.getLogger(ReservationParserServiceImpl.class);


    @Override
    public List<ReservationData> parseReservation(List<Reservation> reservationList) {
        if (reservationList == null) {
            reservationList = new ArrayList<>();
        }
        return parse(reservationList);
    }



    @Override
    public UserReservationResponse parseReservation(List<Reservation> reservationList, UserReservationResponse userReservationResponse) {
        if (userReservationResponse == null) userReservationResponse = new UserReservationResponse();
        if (reservationList == null) {
            reservationList = new ArrayList<>();
        }

        userReservationResponse.setReservationDataArrayList((ArrayList<ReservationData>) parse(reservationList));
        return userReservationResponse;
    }


    private List<ReservationData> parse(List<Reservation> reservationList)
    {
        List<ReservationData> result = new ArrayList<>();
        for (Reservation reservation : reservationList
             ) {

            Calendar toDatePlusDay = Calendar.getInstance();
            toDatePlusDay.setTime(reservation.getToDate());
            toDatePlusDay.add(Calendar.DATE, 1);

            ReservationData reservationData = new ReservationData(reservation.getRoom().getName(),
                    reservation.getRoom().getHotel().getHotelDetail().getName(),
                    reservation.getReservationStatus().getText(),
                    reservation.getFromDate(),
                    toDatePlusDay.getTime(),
                    reservation.getUser());
            result.add(reservationData);
        }


        return result;
    }

    private boolean isSameUser(UserDb currentUser, UserDb nextUser) {
        if(currentUser.equals(nextUser)) return true;
        return false;
    }


    private Date addDay(Date date) {
        Calendar calCurrent=Calendar.getInstance();
        calCurrent.setTime(date);  // set the date
        calCurrent.add(Calendar.DATE, 1); // next day
        return calCurrent.getTime();
    }

    private boolean isNextDay(Date current, Date next) {
        Calendar calCurrent=Calendar.getInstance();
        calCurrent.setTime(current);  // set the date
        calCurrent.add(Calendar.DATE, 1); // next day

        Calendar calNext = Calendar.getInstance();
            calNext.setTime(next);  // set the date

        //log.info("compare Dates " + calCurrent.getTime().getDay() + " :: to :: " + calNext.getTime().getDay());
        return calCurrent.get(Calendar.YEAR) == calNext.get(Calendar.YEAR) &&
                calCurrent.get(Calendar.DAY_OF_YEAR) == calNext.get(Calendar.DAY_OF_YEAR);

    }



}

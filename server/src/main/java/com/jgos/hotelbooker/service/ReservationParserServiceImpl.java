package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.ReservationData;
import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
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
    public UserReservationResponse parseReservation(List<Reservation> reservationList, UserReservationResponse userReservationResponse) {
        List<Room> rooms = new ArrayList<Room>();

        for (Reservation reservation: reservationList)
        {
            if(!rooms.contains(reservation.getRoom()))
            {
                rooms.add(reservation.getRoom());
            }
        }
        log.info("parseReservation: rooms found:"+ rooms.toString());

        for (Room room: rooms)
        {
            ArrayList<Date> datesPerRoom = new ArrayList<Date>();

            for (Reservation reservation: reservationList)
            {
                if (!room.equals(reservation.getRoom())){continue;}
                datesPerRoom.add(reservation.getDate());
            }
            //log.info("parseReservation: dates NO SORT found roomId: " + room.getId() + ": " +datesPerRoom);

            datesPerRoom.sort(Date::compareTo);
            log.info("parseReservation: dates SORT found roomId: " + room.getId() + ": " +datesPerRoom);

            ReservationData reservationData = new ReservationData(datesPerRoom.get(0), room.getName());

            for( int i = 0 ; i < datesPerRoom.size()-1; i++)
            {
                if(!isNextDay(datesPerRoom.get(i), datesPerRoom.get(i+1)))
                {
                    log.info("Another reservation detected,");
                    reservationData.setToDate(addDay(datesPerRoom.get(i)));
                    userReservationResponse.addReservationData(reservationData);

                    //initialize new reservation data
                    reservationData = new ReservationData(datesPerRoom.get(i+1), room.getName());
                }
            }
            reservationData.setToDate(addDay(datesPerRoom.get(datesPerRoom.size()-1)));
            userReservationResponse.addReservationData(reservationData);
        }

        return userReservationResponse;
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

package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.ReservationData;
import com.jgos.hotelbooker.entity.endpoint.UserReservationResponse;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
            TreeMap<Date,DataObject> map = new TreeMap<Date,DataObject>();
           // ArrayList<Date> datesPerRoom = new ArrayList<Date>();

            for (Reservation reservation: reservationList)
            {
                if (!room.equals(reservation.getRoom())){continue;}
                map.put(reservation.getDate(), new DataObject(reservation.getReservationStatus()));
                //datesPerRoom.add(reservation.getDate());
            }
            //log.info("parseReservation: dates NO SORT found roomId: " + room.getId() + ": " +datesPerRoom);

            //datesPerRoom.sort(Date::compareTo);

            log.info("parseReservation: dates SORT found roomId: " + room.getId() + ": " +map.toString());

            ReservationData reservationData = new ReservationData(map.firstKey(), room.getName(),room.getHotel().getHotelDetail().getName(),map.get(map.firstKey()).getReservationStatus().getText() );

            for (Map.Entry<Date, DataObject> entry : map.entrySet())
            {
                if(!isNextDay(entry.getKey(), map.higherKey(entry.getKey())))
                {
                    log.info("Another reservation detected,");
                    reservationData.setToDate(addDay(entry.getKey()));
                    userReservationResponse.addReservationData(reservationData);

                    //initialize new reservation data
                    reservationData = new ReservationData(map.higherKey(entry.getKey()), room.getName(), room.getHotel().getHotelDetail().getName(),entry.getValue().getReservationStatus().getText());
                }
            }
            reservationData.setToDate(addDay(map.lastKey()));
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
        try {
            calNext.setTime(next);  // set the date

        }catch (NullPointerException e)
        {
            //no next, return true anyway
            return true;
        }
        //log.info("compare Dates " + calCurrent.getTime().getDay() + " :: to :: " + calNext.getTime().getDay());
        return calCurrent.get(Calendar.YEAR) == calNext.get(Calendar.YEAR) &&
                calCurrent.get(Calendar.DAY_OF_YEAR) == calNext.get(Calendar.DAY_OF_YEAR);

    }


    private class DataObject {
        ReservationStatus reservationStatus;

        public DataObject(ReservationStatus reservationStatus) {
            this.reservationStatus = reservationStatus;
        }

        public ReservationStatus getReservationStatus() {
            return reservationStatus;
        }

        public void setReservationStatus(ReservationStatus reservationStatus) {
            this.reservationStatus = reservationStatus;
        }

        @Override
        public String toString() {
            return "DataObject{" +
                    "reservationStatus=" + reservationStatus +
                    '}';
        }
    }
}

package com.jgos.hotelbooker.entity.webData;

import com.jgos.hotelbooker.entity.endpoint.ReservationData;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-08-22.
 */
public class WrapperReservationData {

    ArrayList<WrapperReservation> reservations = new ArrayList<>();

    ReservationStatus reservationStatus = ReservationStatus.UNKNOWN;

    public WrapperReservationData() {
    }

    public WrapperReservationData(List<Reservation> reservations) {
        ArrayList<WrapperReservation> tmep = new ArrayList<>();

        for (Reservation res : reservations) {
            tmep.add(new WrapperReservation(res));
        }

        this.reservations = tmep;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "WrapperReservationData{" +
                "reservations=" + reservations +
                ", reservationStatus=" + reservationStatus +
                '}';
    }

    public ArrayList<WrapperReservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<WrapperReservation> reservations) {
        this.reservations = reservations;
    }
}

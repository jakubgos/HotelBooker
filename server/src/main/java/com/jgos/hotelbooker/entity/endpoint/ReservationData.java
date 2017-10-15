package com.jgos.hotelbooker.entity.endpoint;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jgos.hotelbooker.entity.user.UserDb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bos on 2017-07-25.
 */
public class ReservationData {

    private String roomName;
    private String hotelName;
    private String reservationStatus;

    private Date fromDate;
    private Date toDate;

    @JsonIgnore
    private UserDb user;


    public ReservationData() {
    }

    public ReservationData(ReservationData reservationData) {
        this.fromDate = reservationData.getFromDate();
        this.toDate = reservationData.getToDate();
        this.roomName = reservationData.getRoomName();
        this.hotelName = reservationData.getHotelName();
        this.reservationStatus = reservationData.getReservationStatus();
        this.user = user;
    }

    public ReservationData(String roomName, String hotelName, String reservationStatus, Date fromDate, Date toDate, UserDb user ) {
        this.roomName = roomName;
        this.hotelName = hotelName;
        this.reservationStatus = reservationStatus;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.user=user;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public UserDb getUser() {
        return user;
    }

    public void setUser(UserDb user) {
        this.user = user;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        return "ReservationData{" +
                "roomName='" + roomName + '\'' +
                ", fromDate=" + df.format(fromDate) + '\'' +
                ", toDate=" + df.format(toDate) + '\'' +
                ", hotelNAme=" + hotelName+ '\'' +
                ", reservationStatus=" + reservationStatus+ '\'' +
                ", user=" + user.toString()+ '\'' +

                '}';
    }
}

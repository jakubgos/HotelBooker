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
    private long id;

    boolean isRated = false;

    //todo check if can remove
    @JsonIgnore
    private UserDb user;


    public ReservationData() {
    }

    public ReservationData(String roomName, String hotelName, String reservationStatus, Date fromDate, Date toDate, long id, boolean isRated, UserDb user) {
        this.roomName = roomName;
        this.hotelName = hotelName;
        this.reservationStatus = reservationStatus;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.id = id;
        this.isRated = isRated;
        this.user = user;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }

    @Override
    public String toString() {
        return "ReservationData{" +
                "roomName='" + roomName + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", id=" + id +
                ", user=" + user +
                '}';
    }
}

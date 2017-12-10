package com.jgos.hotelBooker.data.serverEntity.endpoint;

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


    public ReservationData() {
    }

    public ReservationData(Date fromDate, String name, String hotelName, String reservationStatus) {
        this.fromDate = fromDate;
        this.roomName = name;
        this.hotelName = hotelName;
        this.reservationStatus = reservationStatus;
    }


    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                ", isRated=" + isRated +
                '}';
    }
}

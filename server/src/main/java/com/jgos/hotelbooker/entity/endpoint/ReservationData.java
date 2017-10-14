package com.jgos.hotelbooker.entity.endpoint;


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

    public ReservationData(Date fromDate, String name,  String hotelName, String reservationStatus) {
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

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        return "ReservationData{" +
                "roomName='" + roomName + '\'' +
                ", fromDate=" + df.format(fromDate) + '\'' +
                ", toDate=" + df.format(toDate) + '\'' +
                ", hotelNAme=" + hotelName+ '\'' +
                ", reservationStatus=" + reservationStatus+ '\'' +
                '}';
    }
}

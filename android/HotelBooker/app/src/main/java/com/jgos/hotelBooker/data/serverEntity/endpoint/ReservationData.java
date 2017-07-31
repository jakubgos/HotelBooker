package com.jgos.hotelBooker.data.serverEntity.endpoint;

/**
 * Created by Bos on 2017-07-25.
 */

public class ReservationData {

    private String roomName;
    private String reservationStatus;

    public ReservationData() {
    }

    @Override
    public String toString() {
        return "ReservationData{" +
                "roomName='" + roomName + '\'' +
                ", reservationStatus=" + reservationStatus +
                '}';
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}

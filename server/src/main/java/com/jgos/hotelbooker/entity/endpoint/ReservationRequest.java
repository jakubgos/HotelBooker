package com.jgos.hotelbooker.entity.endpoint;

/**
 * Created by Bos on 2017-07-23.
 */
public class ReservationRequest {
    private long arrivalTime;
    private long departureTim;
    private long reservationRoomId;

    public ReservationRequest() {
    }

    public ReservationRequest(long arrivalTime, long departureTime, long reservationRoomId) {
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "arrivalTime=" + arrivalTime +
                ", departureTim=" + departureTim +
                ", reservationRoomId=" + reservationRoomId +
                '}';
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartureTim() {
        return departureTim;
    }

    public void setDepartureTim(long departureTim) {
        this.departureTim = departureTim;
    }

    public long getReservationRoomId() {
        return reservationRoomId;
    }

    public void setReservationRoomId(long reservationRoomId) {
        this.reservationRoomId = reservationRoomId;
    }
}
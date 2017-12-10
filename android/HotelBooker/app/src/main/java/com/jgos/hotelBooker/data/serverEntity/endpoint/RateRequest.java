package com.jgos.hotelBooker.data.serverEntity.endpoint;



public class RateRequest {

    public long reservationId;

    private int value;

    public RateRequest() {
    }

    public RateRequest(long reservationId, int value) {
        this.reservationId = reservationId;
        this.value = value;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RateRequest{" +
                "reservationId=" + reservationId +
                ", value=" + value +
                '}';
    }
}

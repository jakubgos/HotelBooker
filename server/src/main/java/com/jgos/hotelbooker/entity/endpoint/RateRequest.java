package com.jgos.hotelbooker.entity.endpoint;

import com.jgos.hotelbooker.entity.user.Reservation;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RateRequest {

    @NotNull
    public long reservationId;

    @NotNull
    @Range(min = 0,max = 10)
    private int value;

    public RateRequest() {
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

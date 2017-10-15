package com.jgos.hotelbooker.entity.webData;

import com.jgos.hotelbooker.entity.endpoint.ReservationData;
import com.jgos.hotelbooker.entity.user.Reservation;

/**
 * Created by Bos on 2017-08-23.
 */
public class WrapperReservation extends ReservationData {

    boolean selected = false;

    public WrapperReservation() {
        super();
    }

    public WrapperReservation(ReservationData res) {
        super(res);
        this.selected = false;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "WrapperReservation{" +
                "selected=" + selected +
                "} " + super.toString();
    }
}

package com.jgos.hotelbooker.entity.endpoint;


import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-07-25.
 */

public class UserReservationResponse {

    ResultStatus status;

    ArrayList<ReservationData> reservationDataArrayList;

    public UserReservationResponse() {
    }

    public UserReservationResponse(ResultStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserReservationResponse{" +
                "status=" + status +
                ", reservationDataArrayList=" + reservationDataArrayList +
                '}';
    }

    public ArrayList<ReservationData> getReservationDataArrayList() {
        return reservationDataArrayList;
    }

    public void setReservationDataArrayList(ArrayList<ReservationData> reservationDataArrayList) {
        this.reservationDataArrayList = reservationDataArrayList;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}

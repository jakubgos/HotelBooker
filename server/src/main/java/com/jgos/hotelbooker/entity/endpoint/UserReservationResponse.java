package com.jgos.hotelbooker.entity.endpoint;


import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-07-25.
 */

public class UserReservationResponse {

    ResultStatus status = ResultStatus.NO_DATA;

    ArrayList<ReservationData> reservationDataArrayList;

    public UserReservationResponse() {
        if (reservationDataArrayList == null)
        {
            reservationDataArrayList = new ArrayList<ReservationData>();
        }
    }

    public UserReservationResponse(ResultStatus status) {
        super();
        this.status = status;
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

    @Override
    public String toString() {
        return "UserReservationResponse{" +
                "status=" + status +
                ", reservationDataArrayList=" + reservationDataArrayList +
                '}';
    }


}

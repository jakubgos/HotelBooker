package com.jgos.hotelBooker.data.serverEntity.endpoint;

import com.jgos.hotelBooker.data.serverEntity.hotel.data.ResultStatus;

/**
 * Created by Bos on 2017-07-22.
 */

public class ReservationResponse {

    ResultStatus status;

    public ReservationResponse() {
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}

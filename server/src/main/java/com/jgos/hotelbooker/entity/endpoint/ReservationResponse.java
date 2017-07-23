package com.jgos.hotelbooker.entity.endpoint;

import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;

/**
 * Created by Bos on 2017-07-23.
 */
public class ReservationResponse {

    ResultStatus status;

    public ReservationResponse() {
        status=ResultStatus.NOT_INITIALED;
    }

    public ReservationResponse(ResultStatus status) {
        this.status=status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "status=" + status +
                '}';
    }
}
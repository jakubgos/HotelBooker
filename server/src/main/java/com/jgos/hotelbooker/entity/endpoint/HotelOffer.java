package com.jgos.hotelbooker.entity.endpoint;


import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-05-28.
 */
public class HotelOffer {

    ResultStatus status;

    private List<HotelData> hotelData = new ArrayList<>();

    public HotelOffer(ResultStatus status) {
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    public List<HotelData> getHotelData() {
        return hotelData;
    }

    public void setHotelData(List<HotelData> hotelData) {
        this.hotelData = hotelData;
    }

    @Override
    public String toString() {
        return "HotelOffer{" +
                "status=" + status +
                ", hotelData=" + hotelData +
                '}';
    }

    public void addHotelData(HotelData hotelData) {
        this.hotelData.add(hotelData);
    }
}

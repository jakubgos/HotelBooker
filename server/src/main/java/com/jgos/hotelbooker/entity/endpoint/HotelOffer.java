package com.jgos.hotelbooker.entity.endpoint;


import com.jgos.hotelbooker.entity.hotel.HotelData;
import com.jgos.hotelbooker.entity.hotel.data.HotelResultStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-05-28.
 */
public class HotelOffer {

    HotelResultStatus status;

    private List<HotelData> hotelData = new ArrayList<>();

    public HotelOffer(HotelResultStatus status) {
        this.status = status;
    }

    public HotelResultStatus getStatus() {
        return status;
    }

    public void setStatus(HotelResultStatus status) {
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

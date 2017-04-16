package com.jgos.hotelBooker.map.interfaces;

import com.jgos.hotelBooker.data.entity.Parking;

import java.util.List;

/**
 * Created by Bos on 2017-03-26.
 */
public interface MapPresenterCallBackFromModel {
    void getParkingListResult(List<Parking> list);

}

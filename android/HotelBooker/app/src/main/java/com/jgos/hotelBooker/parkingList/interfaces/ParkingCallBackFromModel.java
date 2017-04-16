package com.jgos.hotelBooker.parkingList.interfaces;

import com.jgos.hotelBooker.data.entity.Parking;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-03-24.
 */
public interface ParkingCallBackFromModel {
    void onFavoriteParkingResult(ArrayList<Parking> parkings);
}

package com.jgos.hotelBooker.parkingList.interfaces;

import com.jgos.hotelBooker.data.entity.Parking;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-03-21.
 */
public interface ParkingListView {
    void showParkingList(ArrayList<Parking> parkingList);
}

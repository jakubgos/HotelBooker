package com.jgos.hotelBooker.map.interfaces;

import com.jgos.hotelBooker.data.entity.Coordinates;
import com.jgos.hotelBooker.data.entity.LoginData;

/**
 * Created by Bos on 2017-03-26.
 */
public interface MapModel {
    void getParkingList(LoginData loginData, Coordinates coordinates, MapPresenterCallBackFromModel callBack);
}

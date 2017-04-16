package com.jgos.hotelBooker.map.interfaces;

import com.jgos.hotelBooker.data.entity.Coordinates;
import com.jgos.hotelBooker.data.entity.LoginData;

/**
 * Created by Bos on 2017-03-21.
 */
public interface MapPresenter {


    void findClosestParking();

    void onStartup(LoginData loginData);

    void reportLocation(Coordinates coordinates);

    void messageArrivedInd(String message);
}

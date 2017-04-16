package com.jgos.hotelBooker.map;

import com.jgos.hotelBooker.data.entity.Coordinates;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.entity.Parking;
import com.jgos.hotelBooker.data.interfaces.NetworkService;

import com.jgos.hotelBooker.map.interfaces.MapModel;
import com.jgos.hotelBooker.map.interfaces.MapPresenterCallBackFromModel;
import com.jgos.hotelBooker.map.interfaces.ParkingListCallback;

import java.util.List;

/**
 * Created by Bos on 2017-03-26.
 */
public class MapModelImpl implements MapModel {

    NetworkService networkService;

    public MapModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }


    @Override
    public void getParkingList(LoginData loginData, Coordinates coordinates, final MapPresenterCallBackFromModel callBack) {
        networkService.getParkingList(loginData, coordinates , new ParkingListCallback(){
            @Override
            public void parkingListFailed(String s) {

            }

            @Override
            public void getParkingListResult(List<Parking> list) {
                callBack.getParkingListResult(list);
            }
        });
 }
}

package com.jgos.hotelBooker.data.interfaces;

import com.jgos.hotelBooker.data.entity.Coordinates;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.interfaces.LoginServiceResult;
import com.jgos.hotelBooker.map.interfaces.ParkingListCallback;
import com.jgos.hotelBooker.parkingList.interfaces.FavoriteParkingCallback;

/**
 * Created by Bos on 2017-03-04.
 */
public interface NetworkService {
    void login(LoginReqParam loginReqParam, LoginServiceResult loginServiceResult);

    void getFavoriteParking(FavoriteParkingCallback callback);

    void getParkingList(LoginData loginData, Coordinates coordinates, ParkingListCallback parkingListCallback);
}

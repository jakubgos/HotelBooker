package com.jgos.hotelBooker.parkingList;

import com.jgos.hotelBooker.data.entity.Parking;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.parkingList.interfaces.FavoriteParkingCallback;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingCallBackFromModel;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingListModel;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-03-24.
 */
public class ParkingListModelImpl implements ParkingListModel {


    NetworkService networkService;
    public ParkingListModelImpl(NetworkService networkService) {
        this.networkService=networkService;
    }

    @Override
    public void getFavoriteParking(final ParkingCallBackFromModel callBackFromModel) {
        networkService.getFavoriteParking(new FavoriteParkingCallback() {
            @Override
            public void onFavoriteParkingResult(ArrayList<Parking> result) {
                callBackFromModel.onFavoriteParkingResult(result);
            }
        });


    }
}

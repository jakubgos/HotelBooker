package com.jgos.hotelBooker.hotelList.interfaces;

import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelListViewOps {
    abstract void initHotelListView(ArrayList<HotelData> hotelData);

    void showHotelDetailView();

    void showProgressBar();

    void stopProgressBar();

    void showAlertDialogAndFinish(String s);

    void updateListView(List<HotelData> hotelOffer);

    void showReservationActivity();
}

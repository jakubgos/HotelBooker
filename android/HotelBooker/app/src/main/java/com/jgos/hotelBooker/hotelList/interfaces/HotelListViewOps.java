package com.jgos.hotelBooker.hotelList.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelListViewOps {
    void initHotelListView(ArrayList hotelData);

    void showHotelDetailView();

    void showProgressBar();

    void stopProgressBar();

    void showAlertDialogAndFinish(String s);

    void updateListView(List<HotelData> hotelOffer);
}

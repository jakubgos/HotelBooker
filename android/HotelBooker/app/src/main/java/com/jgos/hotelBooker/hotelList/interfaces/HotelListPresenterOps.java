package com.jgos.hotelBooker.hotelList.interfaces;

import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelListPresenterOps {
    void onStartup();

    void listItemSelect(HotelData item);
}

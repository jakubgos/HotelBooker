package com.jgos.hotelBooker.hotelList.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelListModelOps {
    void searchRequest(SearchRequest selectedSearchRequest, LoginData loginData);
}

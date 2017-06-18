package com.jgos.hotelBooker.filter.interfaces;


import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;

import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-05-19.
 */

public interface FilterPresenterOps {
    void onStartup();

    void getCityListResult(List<City> list);

    void getCityListResultFailed(String s);

    void arrivalDateClick();

    void departureDateClick();

    void arrivalDateChange(Date date);

    void departureDateChange(Date date);

    void search(City city, int value);

    void getSearchRequestResult(HotelOffer hotelOffer);

    void getSearchRequestFailure(String s);
}

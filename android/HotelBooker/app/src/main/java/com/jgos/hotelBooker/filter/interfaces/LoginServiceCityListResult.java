package com.jgos.hotelBooker.filter.interfaces;


import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;

import java.util.List;

/**
 * Created by Bos on 2017-05-20.
 */

public interface LoginServiceCityListResult {
    void getCityListResult(List<City> list);

    void failure(String s);
}

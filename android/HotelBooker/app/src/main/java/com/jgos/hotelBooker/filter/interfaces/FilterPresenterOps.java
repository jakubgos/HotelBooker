package com.jgos.hotelBooker.filter.interfaces;

import com.jgos.hotelBooker.data.entity.City;

import java.util.List;

/**
 * Created by Bos on 2017-05-19.
 */

public interface FilterPresenterOps {
    void onStartup();

    void getCityListResult(List<City> list);

    void getCityListResultFailed(String s);
}

package com.jgos.hotelBooker.filter.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.entity.SearchRequest;

/**
 * Created by Bos on 2017-05-19.
 */

public interface FilterModelOps {
    void getCityList(LoginData loginData);

    void searchRequest(SearchRequest searchRequest, LoginData loginData, FilterPresenterOps filterPresenterOps);
}

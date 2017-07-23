package com.jgos.hotelBooker.filter.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;

/**
 * Created by Bos on 2017-05-19.
 */

public interface FilterModelOps {
    void getCityList(LoginData loginData);
}

package com.jgos.hotelBooker.data.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.filter.interfaces.LoginServiceCityListResult;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.interfaces.LoginServiceLoginResult;

/**
 * Created by Bos on 2017-03-04.
 */
public interface NetworkService {
    void login(LoginReqParam loginReqParam, LoginServiceLoginResult loginServiceResult);

    void getCityList(LoginData loginData, LoginServiceCityListResult loginServiceCityListResult);

    void searchRequest(SearchRequest searchRequest, LoginData loginData, SearchRequestResult searchRequestResult);
}

package com.jgos.hotelBooker.filter;


import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;
import com.jgos.hotelBooker.filter.interfaces.FilterModelOps;
import com.jgos.hotelBooker.filter.interfaces.FilterPresenterOps;
import com.jgos.hotelBooker.filter.interfaces.LoginServiceCityListResult;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;

import java.util.List;

/**
 * Created by Bos on 2017-05-19.
 */

class FilterModel implements FilterModelOps {

    private final NetworkService networkService;

    //todo remove:, replace with callback
    FilterPresenterOps filterPresenterOps;

    public FilterModel(FilterPresenterOps filterPresenterOps, NetworkService networkService) {
        this.networkService = networkService;
        this.filterPresenterOps=filterPresenterOps;
    }

    @Override
    public void getCityList(LoginData loginData) {
        networkService.getCityList(loginData, new LoginServiceCityListResult() {
            @Override
            public void getCityListResult(List<City> list) {
                filterPresenterOps.getCityListResult(list);
            }

            @Override
            public void failure(String s) {
                filterPresenterOps.getCityListResultFailed(s);
            }
        });
    }

    @Override
    public void searchRequest(SearchRequest searchRequest, LoginData loginData, final FilterPresenterOps filterPresenterOps) {
        networkService.searchRequest(searchRequest, loginData, new SearchRequestResult() {

            @Override
            public void getSearchRequestResult(HotelOffer hotelOffer) {
                filterPresenterOps.getSearchRequestResult(hotelOffer);
            }

            @Override
            public void searchRequestFailure(String s) {
                filterPresenterOps.getSearchRequestFailure(s);
            }
        });
}
}

package com.jgos.hotelBooker.hotelList;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.filter.interfaces.FilterPresenterOps;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;
import com.jgos.hotelBooker.hotelList.interfaces.HotelListModelOps;
import com.jgos.hotelBooker.hotelList.interfaces.HotelListPresenterOps;

/**
 * Created by Bos on 2017-06-18.
 */

class HotelListModel implements HotelListModelOps {
    private final HotelListPresenterOps hotelListPresenter;

    //todo remove:, replace with callback
    private final NetworkServiceImpl networkService;

    public HotelListModel(HotelListPresenter hotelListPresenter, NetworkServiceImpl networkService) {
        this.hotelListPresenter = hotelListPresenter;
        this.networkService = networkService;
    }

    @Override
    public void searchRequest(SearchRequest searchRequest, LoginData loginData) {
        networkService.searchRequest(searchRequest, loginData, new SearchRequestResult() {

            @Override
            public void getSearchRequestResult(HotelOffer hotelOffer) {
                hotelListPresenter.getSearchRequestResult(hotelOffer);
            }

            @Override
            public void searchRequestFailure(String s) {
                hotelListPresenter.getSearchRequestFailure(s);
            }
        });
    }
}

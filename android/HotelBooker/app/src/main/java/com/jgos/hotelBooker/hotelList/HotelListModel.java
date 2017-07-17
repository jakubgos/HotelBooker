package com.jgos.hotelBooker.hotelList;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.hotelList.interfaces.HotelListModelOps;

/**
 * Created by Bos on 2017-06-18.
 */

class HotelListModel implements HotelListModelOps {
    private final HotelListPresenter hotelListPresenter;

    //todo remove:, replace with callback
    private final NetworkServiceImpl networkService;

    public HotelListModel(HotelListPresenter hotelListPresenter, NetworkServiceImpl networkService) {
        this.hotelListPresenter = hotelListPresenter;
        this.networkService = networkService;
    }
}

package com.jgos.hotelBooker.hotelDetail;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailModelOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailPresenterOps;

/**
 * Created by Bos on 2017-06-18.
 */

class HotelDetailModel implements HotelDetailModelOps {
    private final HotelDetailPresenterOps hoteDetailPresenter;

    //todo remove:, replace with callback
    private final NetworkServiceImpl networkService;

    public HotelDetailModel(HotelDetailPresenterOps hotelDetailPresenterOps, NetworkServiceImpl networkService) {
        this.hoteDetailPresenter = hotelDetailPresenterOps;
        this.networkService=networkService;
    }
}

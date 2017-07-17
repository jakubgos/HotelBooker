package com.jgos.hotelBooker.filter.interfaces;


import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;

/**
 * Created by Bos on 2017-05-26.
 */

public interface SearchRequestResult {
    void getSearchRequestResult(HotelOffer hotelOffer);

    void searchRequestFailure(String s);
}

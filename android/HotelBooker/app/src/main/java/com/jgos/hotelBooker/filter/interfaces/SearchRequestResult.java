package com.jgos.hotelBooker.filter.interfaces;

import com.jgos.hotelBooker.data.entity.HotelOffer;

import java.util.List;

/**
 * Created by Bos on 2017-05-26.
 */

public interface SearchRequestResult {
    void getSearchRequestResult(List<HotelOffer> list);

    void failure(String s);
}

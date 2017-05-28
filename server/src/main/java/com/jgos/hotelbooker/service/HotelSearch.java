package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.HotelOffer;
import com.jgos.hotelbooker.entity.SearchRequest;

/**
 * Created by Bos on 2017-05-28.
 */
public interface HotelSearch {
    HotelOffer search(SearchRequest searchRequest);
}

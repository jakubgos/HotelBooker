package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.HotelOffer;
import com.jgos.hotelbooker.entity.endpoint.SearchRequest;

/**
 * Created by Bos on 2017-05-28.
 */
public interface OfferSearch {
    HotelOffer search(SearchRequest searchRequest);
}

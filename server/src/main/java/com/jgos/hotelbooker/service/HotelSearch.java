package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.HotelOffer;
import com.jgos.hotelbooker.entity.SearchRequest;

import java.util.List;

/**
 * Created by Bos on 2017-05-28.
 */
public interface HotelSearch {
    List<HotelOffer> search(SearchRequest searchRequest);
}

package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.HotelOffer;
import com.jgos.hotelbooker.entity.SearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bos on 2017-05-28.
 */

@Service
public class HotelSearchImpl implements HotelSearch {

    @Override
    public List<HotelOffer> search(SearchRequest searchRequest) {





        return (List<HotelOffer>) new HotelOffer();
    }
}

package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface HotelDetailRepository extends CrudRepository<HotelDetail, Long> {

}

package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.shared.HotelDetail;
import com.jgos.hotelbooker.entity.shared.HotelFacilities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface HotelFacilitiesRepository extends CrudRepository<HotelFacilities, Long> {

}

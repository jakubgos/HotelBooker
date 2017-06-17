package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.shared.City;
import com.jgos.hotelbooker.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    List<Hotel> findByHotelDetailCity (City city);

}

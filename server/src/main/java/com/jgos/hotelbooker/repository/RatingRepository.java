package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.hotel.data.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {

}

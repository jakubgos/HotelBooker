package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.ImagePath;
import com.jgos.hotelbooker.entity.hotel.data.City;
import com.jgos.hotelbooker.entity.user.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface ImageRepository extends CrudRepository<ImagePath, Long> {
    ImagePath findByOwner(UserDb user);

}

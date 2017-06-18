package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.room.RoomFacilities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface RoomFacilitiesRepository extends CrudRepository<RoomFacilities, Long> {

}

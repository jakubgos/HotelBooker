package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findByIdNotIn(Collection<Long> room);

}

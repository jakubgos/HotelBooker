package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.user.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByDateBetween(Date from, Date to);

    List<Reservation> findByDateBetweenAndRoomId(Date from, Date to,long id);

}

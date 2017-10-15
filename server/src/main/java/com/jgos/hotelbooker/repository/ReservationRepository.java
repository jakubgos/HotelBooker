package com.jgos.hotelbooker.repository;


import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {

    @Query("select b from Reservation b where b.fromDate between ?1 and ?2 or b.toDate between ?1 and ?2")
    ArrayList<Reservation> findBetweenDates(Date fromDate, Date toDate);

    ArrayList<Reservation> findByUserEmail(String email);

    ArrayList<Reservation> findByOwnerEmail(String owner);


    ArrayList<Reservation> findByOwnerAndReservationStatus(UserDb owner, ReservationStatus reservationStatus);


}

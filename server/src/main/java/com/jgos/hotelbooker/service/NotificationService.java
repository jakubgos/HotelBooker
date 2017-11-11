package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.user.Reservation;
import org.springframework.stereotype.Service;

/**
 * Created by Bos on 2017-11-10.
 */
public interface NotificationService {
    void addReservation(Reservation reservation);
    void submitNotification();
}

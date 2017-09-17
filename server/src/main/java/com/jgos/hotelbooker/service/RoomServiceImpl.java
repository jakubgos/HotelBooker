package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.controller.ApiHotel;
import com.jgos.hotelbooker.entity.room.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Bos on 2017-09-16.
 */
@Service
public class RoomServiceImpl implements RoomService {
    public RoomServiceImpl() {
    }

    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @Override
    public boolean verifyData(Room room) {

        if (room.getPrice() <= 0) {
            log.info("verifyData room.getPrice()<=0 ");
            return false;
        }

        if (room.getName().equals("") || room.getDescription().equals("")) {
            log.info("verifyData room.getName() or getDescription is null ");
            return false;
        }
        return true;
    }
}

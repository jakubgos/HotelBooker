package com.jgos.hotelBooker.hotelDetail.interfaces;

import com.jgos.hotelBooker.data.serverEntity.hotel.HotelDetail;
import com.jgos.hotelBooker.data.serverEntity.room.Room;

/**
 * Created by Bos on 2017-06-18.
 */

public interface HotelDetailViewOps {
    void prepareHotelData(HotelDetail hotelDetail);

    void prepareRoomData(Room room);

    void showSnackBar();

    void showConfirmDialog();

    void showAlertDialog(String s, boolean b);

    void showProgressDialog();

    void dismissProgressDialog();

    void showReservationSuccessDialog();

    void endActivity();

    void showReservationActivity();
}

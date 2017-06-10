package com.jgos.hotelbooker.entity.shared;

import com.jgos.hotelbooker.entity.HotelDetail;
import com.jgos.hotelbooker.entity.Room;

import java.util.List;

/**
 * Created by Bos on 2017-06-11.
 */
public class HotelData {

    private HotelDetail hotelDetail;
    private List<Room> roomList;

    public HotelData(HotelDetail hotelDetail, List<Room> roomList) {
        this.hotelDetail = hotelDetail;
        this.roomList = roomList;
    }

    public HotelData() {
    }

    public HotelDetail getHotelDetail() {
        return hotelDetail;
    }

    public void setHotelDetail(HotelDetail hotelDetail) {
        this.hotelDetail = hotelDetail;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "HotelData{" +
                "hotelDetail=" + hotelDetail +
                ", roomList=" + roomList +
                '}';
    }
}

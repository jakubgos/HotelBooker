package com.jgos.hotelbooker.entity.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jgos.hotelbooker.entity.room.Room;
import com.jgos.hotelbooker.entity.user.UserDb;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bos on 2017-05-28.
 */

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @OneToOne
    private HotelDetail hotelDetail;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> roomList;

    @JsonIgnore
    @OneToOne
    private UserDb owner;

    public Hotel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "Hotel{" +
                "id=" + id +
                ", hotelDetail=" + hotelDetail +
                ", roomList=" + roomList +
                ", owner=" + owner +
                '}';
    }

    public UserDb getOwner() {
        return owner;
    }

    public void setOwner(UserDb owner) {
        this.owner = owner;
    }
}

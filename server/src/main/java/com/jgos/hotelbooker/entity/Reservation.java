package com.jgos.hotelbooker.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bos on 2017-06-04.
 */

@Entity
public class Reservation {
    public Reservation() {
    }

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    UserDb user;

    @Temporal(TemporalType.DATE)
    Date date;

    public Reservation(Room room, UserDb user, Date date) {
        this.room = room;
        this.user = user;
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public UserDb getUser() {
        return user;
    }

    public void setUser(UserDb user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}

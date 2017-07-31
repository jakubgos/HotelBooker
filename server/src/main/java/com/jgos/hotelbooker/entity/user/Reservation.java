package com.jgos.hotelbooker.entity.user;

import com.jgos.hotelbooker.entity.room.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Bos on 2017-06-04.
 */

@Entity
public class Reservation {
    @NotNull
    @ManyToOne
    UserDb user;
    @NotNull
    @Temporal(TemporalType.DATE)
    Date date;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @NotNull
    @ManyToOne
    private Room room;

    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Reservation(Room room, UserDb user, Date date,ReservationStatus reservationStatus) {

        this.room = room;
        this.user = user;
        this.date = date;
        this.reservationStatus = reservationStatus;

    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public long getId() {
        return id;
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

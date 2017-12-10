package com.jgos.hotelbooker.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jgos.hotelbooker.entity.hotel.Feedback;
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
    Date fromDate;

    @Temporal(TemporalType.DATE)
    Date toDate;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long id;

    @NotNull
    @ManyToOne
    public Room room;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public UserDb owner;

    @Enumerated(EnumType.ORDINAL)
    public ReservationStatus reservationStatus;

    @ManyToOne(cascade=CascadeType.ALL)
    Feedback feedback;

    public Reservation() {

    }

    public Reservation(UserDb user, Date fromDate, Date toDate, Room room, UserDb owner, ReservationStatus reservationStatus, Feedback feedback) {
        this.user = user;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.room = room;
        this.owner = owner;
        this.reservationStatus = reservationStatus;
        this.feedback = feedback;
    }

    public Reservation(Reservation res) {
        this.id=res.getId();
        this.user = res.getUser();
        this.fromDate = res.getFromDate();
        this.toDate = res.getToDate();
        this.room = res.getRoom();
        this.owner = res.getOwner();
        this.reservationStatus = res.getReservationStatus();
        this.feedback = res.feedback;
    }

    public UserDb getUser() {
        return user;
    }

    public void setUser(UserDb user) {
        this.user = user;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public long getId() {
        return id;
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

    public UserDb getOwner() {
        return owner;
    }

    public void setOwner(UserDb owner) {
        this.owner = owner;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "user=" + user +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", id=" + id +
                ", room=" + room +
                ", owner=" + owner +
                ", reservationStatus=" + reservationStatus +
                ", feedback=" + feedback +
                '}';
    }
}

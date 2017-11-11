package com.jgos.hotelbooker.entity.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jgos.hotelbooker.controller.ApiHotel;
import com.jgos.hotelbooker.entity.user.ReservationStatus;
import com.jgos.hotelbooker.entity.user.UserDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * Created by Bos on 2017-11-10.
 */
@Entity
public class Notification {

    @Transient
    private static final Logger log = LoggerFactory.getLogger(ApiHotel.class);

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @JsonIgnore
    @OneToOne
    private UserDb owner;
    private boolean isActive;

    //WAIT_FOR_CONFIRMATION("Oczekuje na weryfikacje"),
    private String WAIT_FOR_CONFIRMATION_change;

    //WAIT_FOR_PAYMENT("Oczekuje na wpłatę"),
    private String WAIT_FOR_PAYMENT_change;

    //RESERVATION_DONE("Zarezerwowane"),
    private String RESERVATION_DONE_change;

    //RESERVATION_REJECTED("Odrzucono przez hotel"),
    private String RESERVATION_REJECTED_change;

    //RESERVATION_FINISHED("Zrealizowano"),
    private String RESERVATION_FINISHED_change;

    //UNKNOWN("unknown");
    private String UNKNOWN_change;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public Notification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDb getOwner() {
        return owner;
    }

    public void setOwner(UserDb owner) {
        this.owner = owner;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getIsActive() {
        return isActive;
    }



    public String getWAIT_FOR_CONFIRMATION_change() {
        return WAIT_FOR_CONFIRMATION_change;
    }

    public void setWAIT_FOR_CONFIRMATION_change(String WAIT_FOR_CONFIRMATION_change) {
        this.WAIT_FOR_CONFIRMATION_change = WAIT_FOR_CONFIRMATION_change;
    }

    public String getWAIT_FOR_PAYMENT_change() {
        return WAIT_FOR_PAYMENT_change;
    }

    public void setWAIT_FOR_PAYMENT_change(String WAIT_FOR_PAYMENT_change) {
        this.WAIT_FOR_PAYMENT_change = WAIT_FOR_PAYMENT_change;
    }

    public String getRESERVATION_DONE_change() {
        return RESERVATION_DONE_change;
    }

    public void setRESERVATION_DONE_change(String RESERVATION_DONE_change) {
        this.RESERVATION_DONE_change = RESERVATION_DONE_change;
    }

    public String getRESERVATION_REJECTED_change() {
        return RESERVATION_REJECTED_change;
    }

    public void setRESERVATION_REJECTED_change(String RESERVATION_REJECTED_change) {
        this.RESERVATION_REJECTED_change = RESERVATION_REJECTED_change;
    }

    public String getRESERVATION_FINISHED_change() {
        return RESERVATION_FINISHED_change;
    }

    public void setRESERVATION_FINISHED_change(String RESERVATION_FINISHED_change) {
        this.RESERVATION_FINISHED_change = RESERVATION_FINISHED_change;
    }

    public String getUNKNOWN_change() {
        return UNKNOWN_change;
    }

    public void setUNKNOWN_change(String UNKNOWN_change) {
        this.UNKNOWN_change = UNKNOWN_change;
    }



    public String getMessage(ReservationStatus reservationStatus) {

        switch (reservationStatus)
        {
            case WAIT_FOR_CONFIRMATION:
                return WAIT_FOR_CONFIRMATION_change;
            case WAIT_FOR_PAYMENT:
                return WAIT_FOR_PAYMENT_change;
            case RESERVATION_DONE:
                return RESERVATION_DONE_change;
            case RESERVATION_REJECTED:
                return RESERVATION_REJECTED_change;
            case RESERVATION_FINISHED:
                return RESERVATION_FINISHED_change;
            case UNKNOWN:
                return UNKNOWN_change;
            default:
                log.error("getMessage unexpected status recieved "+ reservationStatus );
                return "";
        }
    }
}

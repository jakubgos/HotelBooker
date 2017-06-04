package com.jgos.hotelbooker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Bos on 2017-06-04.
 */
@Entity
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private long latitude;
    private long longitude;

    public Coordinates(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates() {
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}



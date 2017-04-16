package com.jgos.hotelBooker.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Bos on 2017-03-25.
 */

public class Coordinates {

    private double latitude=-1;
    private double longitude=-1;

    public Coordinates() {
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonIgnore
    public boolean isValid(){
        return latitude !=-1 && longitude !=-1;

    }
}

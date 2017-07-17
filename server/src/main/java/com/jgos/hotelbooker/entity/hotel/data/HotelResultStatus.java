package com.jgos.hotelbooker.entity.hotel.data;

/**
 * Created by Bos on 2017-06-11.
 */
public enum HotelResultStatus {
    OK(0), NO_DATA(1);

    private int value;

    HotelResultStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
};
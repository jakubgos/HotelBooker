package com.jgos.hotelbooker.entity.hotel.data;

/**
 * Created by Bos on 2017-06-11.
 */
public enum ResultStatus {
    OK(0), NO_DATA(1), RESERVATION_NOT_POSSIBLE(2), NOT_INITIALED(99);

    private int value;

    ResultStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
};
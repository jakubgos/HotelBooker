package com.jgos.hotelBooker.data.serverEntity.hotel.data;



/**
 * Created by Bos on 2017-06-17.
 */
public class Rating {

    private long id;

    private int value;

    public Rating() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}

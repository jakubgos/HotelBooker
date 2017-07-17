package com.jgos.hotelBooker.data.serverEntity.hotel.data;


/**
 * Created by Bos on 2017-05-20.
 */
public class City {

    private long id;
    private String name;

    public City() {
    }

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        //Do not change
        return name;
    }
}

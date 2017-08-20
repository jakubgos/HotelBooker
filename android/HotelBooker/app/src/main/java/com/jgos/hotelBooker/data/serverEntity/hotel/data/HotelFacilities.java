package com.jgos.hotelBooker.data.serverEntity.hotel.data;

/**
 * Created by Bos on 2017-06-17.
 */


public class HotelFacilities {
    //Dining,Vending,Exercise,Recreation,SwimmingPool,Parking
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HotelFacilities() {
    }

    public HotelFacilities(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HotelFacilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

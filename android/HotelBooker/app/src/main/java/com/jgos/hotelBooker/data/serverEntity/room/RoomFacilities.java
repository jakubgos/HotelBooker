package com.jgos.hotelBooker.data.serverEntity.room;


/**
 * Created by Bos on 2017-06-17.
 */
public class RoomFacilities {
    //KitchenFacilities,Television,InternetAccess,Hairdryer,Towels

    private long id;

    private String name;

    public RoomFacilities() {
    }

    public RoomFacilities(String name) {
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
        return "RoomFacilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

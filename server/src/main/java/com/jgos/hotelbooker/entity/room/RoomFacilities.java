package com.jgos.hotelbooker.entity.room;

import javax.persistence.*;

/**
 * Created by Bos on 2017-06-17.
 */
@Entity
public class RoomFacilities {
    //KitchenFacilities,Television,InternetAccess,Hairdryer,Towels

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(nullable = false)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoomFacilities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

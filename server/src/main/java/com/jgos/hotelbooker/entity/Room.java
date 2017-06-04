package com.jgos.hotelbooker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Bos on 2017-05-28.
 */
@Entity
public class Room {
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    private String name;
    private String description;
    private int size;

    public Room() {
    }

    public Room(String name, String description, int size) {
        this.name = name;
        this.description = description;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                '}';
    }
}

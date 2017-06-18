package com.jgos.hotelbooker.entity.room;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    private List<RoomFacilities> roomFacilities;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<RoomFacilities> getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(List<RoomFacilities> roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", roomFacilities=" + roomFacilities +
                '}';
    }
}
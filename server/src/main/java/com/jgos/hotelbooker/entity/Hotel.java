package com.jgos.hotelbooker.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bos on 2017-05-28.
 */

@Entity
public class Hotel {

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    @ManyToOne(optional = false)
    private City city;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String adress;

    @OneToOne
    private Coordinates coordinates;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> roomList;

    public Hotel() {
    }

    public Hotel(City city, String name, String description, String adress, Coordinates coordinates, List<Room> roomList) {
        this.city = city;
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.coordinates = coordinates;
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", city=" + city +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adress='" + adress + '\'' +
                ", cords=" + coordinates +
                ", roomList=" + roomList +
                '}';
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
}

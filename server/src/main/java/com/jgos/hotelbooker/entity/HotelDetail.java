package com.jgos.hotelbooker.entity;

import javax.persistence.*;

/**
 * Created by Bos on 2017-06-11.
 */

@Entity
public class HotelDetail {

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)

    String description;

    @Column(nullable = false)
    String address;

    @OneToOne
    private Coordinates coordinates;

    @ManyToOne(optional = false)
    private City city;

    public HotelDetail() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "HotelDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", coordinates=" + coordinates +
                ", city=" + city +
                '}';
    }

    public HotelDetail(String name, String description, String address, Coordinates coordinates, City city) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.coordinates = coordinates;
        this.city = city;
    }
}

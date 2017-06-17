package com.jgos.hotelbooker.entity.shared;


import javax.persistence.*;
import java.util.List;

/**
 * Created by Bos on 2017-06-11.
 */

@Entity
public class HotelDetail {

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @OneToOne
    private Coordinates coordinates;

    @ManyToOne(optional = false)
    private City city;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FoodOffer> foodOffer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<HotelFacilities> hotelFacilities;

    @ManyToOne
    private Rating rating;

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


    public List<FoodOffer> getFoodOffer() {
        return foodOffer;
    }

    public void setFoodOffer(List<FoodOffer> foodOffer) {
        this.foodOffer = foodOffer;
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
                ", foodOffer=" + foodOffer +
                ", hotelFacilities=" + hotelFacilities +
                ", rating=" + rating +
                '}';
    }

    public List<HotelFacilities> getFacilities() {
        return hotelFacilities;
    }

    public void setFacilities(List<HotelFacilities> facilities) {
        this.hotelFacilities = facilities;
    }
}

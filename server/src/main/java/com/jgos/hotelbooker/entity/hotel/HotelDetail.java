package com.jgos.hotelbooker.entity.hotel;


import com.jgos.hotelbooker.entity.hotel.data.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-06-11.
 */

@Entity
public class HotelDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false, length = 512)
    private String description;

    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    private Coordinates coordinates;

    @ManyToOne(optional = false)
    private City city;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<FoodOffer> foodOffer;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<HotelFacilities> hotelFacilities;

    @ManyToOne
    private Rating rating;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;


    public HotelDetail() {
    }

    public void addFeedback(Feedback feedback)
    {
        if (feedbackList == null) {
            feedbackList = new ArrayList<Feedback>();
        }
        feedbackList.add(feedback);
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

    public List<HotelFacilities> getHotelFacilities() {
        return hotelFacilities;
    }

    public void setHotelFacilities(List<HotelFacilities> hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
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
                ", feedbackList=" + feedbackList +
                '}';
    }
}

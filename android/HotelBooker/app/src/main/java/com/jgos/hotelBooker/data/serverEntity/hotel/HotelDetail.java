package com.jgos.hotelBooker.data.serverEntity.hotel;


import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.Coordinates;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.FoodOffer;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.HotelFacilities;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.Rating;

import java.util.List;

/**
 * Created by Bos on 2017-06-11.
 */

public class HotelDetail {


    private long id;

    private String name;

    private String description;

    private String address;

    private Coordinates coordinates;

    private City city;

    private List<FoodOffer> foodOffer;

    private List<HotelFacilities> hotelFacilities;

    private Rating rating;

    private List<Feedback> feedbackList;

    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public HotelDetail() {
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
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
}

package com.jgos.hotelBooker.data.serverEntity.endpoint;


import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;

/**
 * Created by Bos on 2017-05-28.
 */
public class SearchRequest {

    private final City city;
    private final long arrivalTime;
    private final long departureTime;
    private final int numberOfPeople;


    public SearchRequest(City city, long arrivalTime, long departureTime, int numberOfPeople) {
        this.city = city;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.numberOfPeople = numberOfPeople;
    }

    public SearchRequest() {
        city = new City();
        arrivalTime = 0;
        departureTime = 0;
        numberOfPeople = -1;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }


    public City getCity() {
        return city;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "city=" + city +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
}

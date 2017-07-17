package com.jgos.hotelbooker.entity.endpoint;

import com.jgos.hotelbooker.entity.hotel.data.City;

import javax.validation.Valid;
import javax.validation.constraints.Min;


/**
 * Created by Bos on 2017-05-28.
 */
public class SearchRequest {

    @Valid
    private final City city;
    @Min(0)
    private final long arrivalTime;
    @Min(0)
    private final long departureTime;
    @Min(1)
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

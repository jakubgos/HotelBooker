package com.jgos.hotelBooker.filter.interfaces;


import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-05-19.
 */

public interface FilterViewOps {
    void initSpinner(List<City> list);

    void showCityLoadProgressBar(boolean b);

    void makeToast(String s);

    Date getCurrentDate();

    void displayArrivalDate(String s);

    void displayDepartureDate(String s);

    void showCalendarDialog(Calendar current, Calendar minDate, String title);
}

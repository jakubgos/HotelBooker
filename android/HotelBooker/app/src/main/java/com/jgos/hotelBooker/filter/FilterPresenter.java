package com.jgos.hotelBooker.filter;

import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.entity.DialogChoice;

import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.City;
import com.jgos.hotelBooker.filter.interfaces.FilterPresenterOps;
import com.jgos.hotelBooker.filter.interfaces.FilterViewOps;
import com.jgos.hotelBooker.filter.interfaces.FilterModelOps;
import com.jgos.hotelBooker.storage.Storage;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Bos on 2017-05-19.
 */

class FilterPresenter implements FilterPresenterOps {
    private final WeakReference<FilterViewOps> filterViewOps;
    private final FilterModelOps filterModelOps;
    private final Handler handler = new Handler();
    private final String DATA_FORMAT = "yyyy-MM-dd";
    private Calendar arrivalCalendar = null;
    private Calendar departureCalendar = null;
    public FilterPresenter(FilterViewOps filterViewOps ) {
        this.filterViewOps=new WeakReference<>(filterViewOps);
        this.filterModelOps = new FilterModel(this, new NetworkServiceImpl());
    }

    private FilterViewOps  getView() throws NullPointerException {
        if ( filterViewOps != null )
            return filterViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup() {
        getView().showCityLoadProgressBar(true);


        Date arrivalDate = new Date();
        Date departureDate = new Date();

        arrivalCalendar = Calendar.getInstance();
        arrivalCalendar.setTime(arrivalDate);

        departureCalendar = Calendar.getInstance();
        departureCalendar.setTime(departureDate);
        departureCalendar.add(Calendar.DATE, 1);
        departureDate = departureCalendar.getTime();

        getView().displayArrivalDate( DateFormat.format(DATA_FORMAT,arrivalDate).toString());
        getView().displayDepartureDate( DateFormat.format(DATA_FORMAT,departureDate).toString());

        filterModelOps.getCityList(Storage.getInstance().getLoginData());

        //getView().initSpinner();
    }

    @Override
    public void getCityListResult(final List<City> list) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showCityLoadProgressBar(false);
                getView().initSpinner(list);
            }
        });

    }

    @Override
    public void getCityListResultFailed(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showCityLoadProgressBar(false);
                getView().makeToast(s);
            }
        });
    }

    @Override
    public void arrivalDateClick() {
        getView().showCalendarDialog(arrivalCalendar, null, DialogChoice.ARRIVAL.name());
    }

    @Override
    public void departureDateClick() {
        Calendar minCalendarTime= Calendar.getInstance();
        minCalendarTime.setTime(arrivalCalendar.getTime());
        minCalendarTime.add(Calendar.DATE, 1);

        getView().showCalendarDialog(departureCalendar, minCalendarTime, DialogChoice.DEPARTURE.name());

    }

    @Override
    public void arrivalDateChange(Date date) {
        arrivalCalendar.setTime(date);
        getView().displayArrivalDate( DateFormat.format(DATA_FORMAT,date).toString());
        if(arrivalCalendar.getTime().compareTo(departureCalendar.getTime()) >= 0)
        {
            Log.d("MyApp_filter","arrivalCalendar > departureCalendar, update departureCalendar ");
            departureCalendar.setTime(arrivalCalendar.getTime());
            departureCalendar.add(Calendar.DATE, 1);

            getView().displayDepartureDate( DateFormat.format(DATA_FORMAT,departureCalendar.getTime()).toString());
        }

    }

    @Override
    public void departureDateChange(Date date) {
        departureCalendar.setTime(date);
        getView().displayDepartureDate( DateFormat.format(DATA_FORMAT,date).toString());

    }

    @Override
    public void search(City city, int numberOfPeople) {
        getView().showCityLoadProgressBar(true);
        SearchRequest searchRequest = new SearchRequest(city,arrivalCalendar.getTime().getTime(),departureCalendar.getTime().getTime(), numberOfPeople);
        filterModelOps.searchRequest(searchRequest, Storage.getInstance().getLoginData() , this);

    }

    @Override
    public void getSearchRequestResult(final HotelOffer hotelOffer) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Storage.getInstance().save(hotelOffer);
                getView().showCityLoadProgressBar(false);
                getView().showHotelListView();
            }
        });
    }

    @Override
    public void getSearchRequestFailure(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showCityLoadProgressBar(false);

                getView().makeToast(s);
            }
        });
    }
}

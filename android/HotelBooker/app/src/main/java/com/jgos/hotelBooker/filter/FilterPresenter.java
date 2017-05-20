package com.jgos.hotelBooker.filter;

import android.os.Handler;
import android.text.format.DateFormat;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.entity.City;
import com.jgos.hotelBooker.filter.interfaces.FilterPresenterOps;
import com.jgos.hotelBooker.filter.interfaces.FilterViewOps;
import com.jgos.hotelBooker.filter.interfaces.FilterModelOps;
import com.jgos.hotelBooker.storage.Storage;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
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


        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        getView().displayArrivalDate( DateFormat.format(DATA_FORMAT,dt).toString());

        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        getView().displayDepartureDate( DateFormat.format(DATA_FORMAT,dt).toString());

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
    public void arrivalDateChange() {

    }

    @Override
    public void departureDateChange() {

    }
}

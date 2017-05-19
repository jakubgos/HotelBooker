package com.jgos.hotelBooker.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.entity.City;
import com.jgos.hotelBooker.filter.interfaces.FilterPresenterOps;
import com.jgos.hotelBooker.filter.interfaces.FilterViewOps;

import java.util.List;

public class FilterActivity extends AppCompatActivity implements FilterViewOps {


    FilterPresenterOps mPresenter;
    Spinner citySpinner;

    private View mCityLoadProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyApp_Filter"," FilterActivity onCreate invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        citySpinner = (Spinner)this.findViewById(R.id.citySpinner);

        mCityLoadProgressView = findViewById(R.id.city_load_progress);

        setupMVP();

        mPresenter.onStartup();
    }

    private void setupMVP() {
        mPresenter = new FilterPresenter(this);
    }

    @Override
    public void initSpinner(List<City> list) {
        Log.d("MyApp_Filter","initSpinner invoked");
        citySpinner = (Spinner)this.findViewById(R.id.citySpinner);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, list);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        citySpinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void showCityLoadProgressBar(boolean show) {
        mCityLoadProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

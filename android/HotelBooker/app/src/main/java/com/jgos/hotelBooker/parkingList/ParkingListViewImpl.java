package com.jgos.hotelBooker.parkingList;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.entity.Parking;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingListPresenter;
import com.jgos.hotelBooker.parkingList.interfaces.ParkingListView;

import java.util.ArrayList;

public class ParkingListViewImpl extends AppCompatActivity implements ParkingListView {

    ParkingListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);


        setupMVP();

        mPresenter.onStartUp();
    }

    private void setupMVP() {
        ParkingListPresenter presenter = new ParkingListPresenterImpl(this);
        mPresenter = presenter;
    }


    @Override
    public void showParkingList(ArrayList<Parking> parkingList) {
        final ListView listview = (ListView) findViewById(R.id.listview);

        final StableArrayAdapter adapter = new StableArrayAdapter(getApplicationContext(), parkingList);
        listview.setAdapter(adapter);


   }


    private class StableArrayAdapter extends BaseAdapter {

        ArrayList<Parking> parkingList;
        Context mContext;

        public StableArrayAdapter(Context context,  ArrayList<Parking> parkingList) {
            this.mContext = context;
            this.parkingList = parkingList;

        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return parkingList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return parkingList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Parking parking = parkingList.get(position);
            convertView = LayoutInflater.from(mContext).inflate(R.layout.parking_row_list,null);

            TextView main_test = (TextView) convertView.findViewById(R.id.main_text);
            TextView add_text = (TextView) convertView.findViewById(R.id.additional_text);

            main_test.setText(parking.getName());
            add_text.setText(parking.getAvailablePlaces() + "/" + parking.getMaxCapacity() );
            return convertView;
        }

    }
}

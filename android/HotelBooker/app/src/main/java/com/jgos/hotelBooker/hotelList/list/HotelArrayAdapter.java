package com.jgos.hotelBooker.hotelList.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-07-02.
 */

public class HotelArrayAdapter extends ArrayAdapter<HotelData> {

    static class ViewHolder {
        public TextView hotelName;
        public TextView hotelAdress;
        public TextView price;
        public TextView rating;
        public TextView roomName;

    }

    public HotelArrayAdapter(Context context, ArrayList<HotelData> hotelData) {
        super(context, 0, hotelData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.custom_hotel_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.hotelName = (TextView) rowView.findViewById(R.id.hotelName);
            viewHolder.hotelAdress = (TextView) rowView.findViewById(R.id.hotelAddress);
            viewHolder.price = (TextView) rowView.findViewById(R.id.price);
            viewHolder.rating = (TextView) rowView.findViewById(R.id.rating);
            viewHolder.roomName = (TextView) rowView.findViewById(R.id.roomName);


            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        HotelData hotelData = getItem(position);
        viewHolder.hotelName.setText(hotelData.getHotelDetail().getName());
        viewHolder.hotelAdress.setText(hotelData.getHotelDetail().getCity().getName());
        viewHolder.price.setText(hotelData.getRoomList().get(0).getPrice() + "PLN");
        viewHolder.rating.setText(hotelData.getHotelDetail().getRating().getValue() + " \\ 5");
        viewHolder.roomName.setText(hotelData.getRoomList().get(0).getName());

        return rowView;
    }

}

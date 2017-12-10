package com.jgos.hotelBooker.hotelList.list;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.hotel.Feedback;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-07-02.
 */

public class HotelArrayAdapter extends ArrayAdapter<HotelData> {

    public HotelArrayAdapter(Context context, ArrayList<HotelData> hotelData) {
        super(context, 0, hotelData);
    }


    public void swapItems(List<HotelData> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.custom_hotel_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.hotelName = (TextView) rowView.findViewById(R.id.hotelName);
            viewHolder.hotelAdress = (TextView) rowView.findViewById(R.id.hotelAddress);
            viewHolder.price = (TextView) rowView.findViewById(R.id.price);
            viewHolder.rating = (TextView) rowView.findViewById(R.id.rating);
            viewHolder.roomName = (TextView) rowView.findViewById(R.id.roomName);
            viewHolder.ratingBar = (RatingBar) rowView.findViewById(R.id.hotel_list_item_rate);

            //workaround to change color
            LayerDrawable stars = (LayerDrawable) viewHolder.ratingBar.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        HotelData hotelData = getItem(position);
        viewHolder.hotelName.setText(hotelData.getHotelDetail().getName());
        viewHolder.hotelAdress.setText(hotelData.getHotelDetail().getCity().getName());
        viewHolder.price.setText(hotelData.getRoomList().get(0).getPrice() + "PLN");
        viewHolder.rating.setText(calculateRate(hotelData.getHotelDetail().getFeedbackList()) + " \\ 10");
        viewHolder.roomName.setText(hotelData.getRoomList().get(0).getName());
        viewHolder.ratingBar.setRating(hotelData.getHotelDetail().getRating().getValue());
        return rowView;
    }

    static class ViewHolder {
        public TextView hotelName;
        public TextView hotelAdress;
        public TextView price;
        public TextView rating;
        public TextView roomName;
        public RatingBar ratingBar;

    }

    private float calculateRate(List<Feedback> feedbackList){
        float sum =0;
        for (Feedback fb: feedbackList
             ) {
            sum += fb.getValue();
        }

        float result = 0;

        if(feedbackList.size() > 0)
            result = sum/feedbackList.size();
        return (float) (Math.round(result * 100.0) / 100.0);
    }

}

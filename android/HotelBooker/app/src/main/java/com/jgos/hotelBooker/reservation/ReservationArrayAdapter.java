package com.jgos.hotelBooker.reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-07-25.
 */

class ReservationArrayAdapter extends ArrayAdapter<ReservationData> {

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public ReservationArrayAdapter(Context context, ArrayList<ReservationData> reservationDataArrayList) {
        super(context, 0, reservationDataArrayList);
    }


    public void swapItems(List<ReservationData> items) {
        clear();
        addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.custom_reservation_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.roomName = (TextView) rowView.findViewById(R.id.reservation_room_name);
            viewHolder.status = (TextView) rowView.findViewById(R.id.reservation_status);
            viewHolder.hotelName = (TextView) rowView.findViewById(R.id.reservation_hotel_name);
            viewHolder.fromDate = (TextView) rowView.findViewById(R.id.reservation_fromDate);
            viewHolder.toDate = (TextView) rowView.findViewById(R.id.reservation_toDate);


            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        ReservationData reservationData = getItem(position);
        viewHolder.roomName.setText(reservationData.getRoomName());
        viewHolder.status.setText(reservationData.getReservationStatus());
        viewHolder.hotelName.setText(reservationData.getHotelName());
        viewHolder.fromDate.setText(df.format(reservationData.getFromDate()));
        viewHolder.toDate.setText(df.format(reservationData.getToDate()));

        return rowView;
    }

    static class ViewHolder {
        public TextView roomName;
        public TextView status;
        public TextView hotelName;
        public TextView fromDate;
        public TextView toDate;

    }
}

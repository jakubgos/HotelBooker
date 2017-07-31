package com.jgos.hotelBooker.reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bos on 2017-07-25.
 */

class ReservationArrayAdapter extends ArrayAdapter<ReservationData> {

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


            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        ReservationData reservationData = getItem(position);
        viewHolder.roomName.setText(reservationData.getRoomName());
        viewHolder.status.setText(reservationData.getReservationStatus());
        return rowView;
    }

    static class ViewHolder {
        public TextView roomName;
        public TextView status;
    }
}

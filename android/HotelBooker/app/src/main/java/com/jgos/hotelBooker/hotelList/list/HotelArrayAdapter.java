package com.jgos.hotelBooker.hotelList.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.hotel.Feedback;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
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
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.list_view_hotel_icon);
            viewHolder.mProgressView = (View) rowView.findViewById(R.id.list_view_prgress);

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

        if (viewHolder.imageView != null && hotelData.getHotelDetail().getPicturePath() != null && viewHolder.mProgressView.getTag() == null ) {
            viewHolder.mProgressView.setTag(true);
            new ImageDownloaderTask(viewHolder.imageView, viewHolder.mProgressView).execute(hotelData.getHotelDetail().getPicturePath());
        }

        return rowView;
    }

    static class ViewHolder {
        public TextView hotelName;
        public TextView hotelAdress;
        public TextView price;
        public TextView rating;
        public TextView roomName;
        public RatingBar ratingBar;
        public View mProgressView;

        ImageView imageView;


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


    class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private final WeakReference<View> mProgressReference;
        public ImageDownloaderTask(ImageView imageView, View progressBar) {
            imageViewReference = new WeakReference<ImageView>(imageView);
            mProgressReference = new  WeakReference<View>(progressBar);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadBitmap(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null && mProgressReference != null) {
                ImageView imageView = imageViewReference.get();
                View progressBar = mProgressReference.get();

                if (imageView != null && progressBar != null) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                        progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                    } else {
                        Log.e("ImageDownloaderTask", "ERROR!");
                        //Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                        //imageView.setImageDrawable(placeholder);
                    }
                }
            }
        }
    }

    private Bitmap downloadBitmap(String url) {
        Log.e("ImageDownloaderTask", "DOWNLOAD STARTING");
        HttpURLConnection urlConnection = null;
        try {
            String fullUrl = url.replace("\\", "/");

            URL uri = new URL("http://80.211.216.60:8080/"+ fullUrl);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Log.e("ImageDownloaderTask", "DOWNLOAD finished!");

                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}

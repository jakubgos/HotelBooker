package com.jgos.hotelBooker.hotelDetail;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelDetail;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.FoodOffer;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.HotelFacilities;
import com.jgos.hotelBooker.storage.Storage;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailPresenterOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailViewOps;


public class HotelDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HotelDetailViewOps {

    private HotelDetailPresenterOps mPresenter;

    private TextView hotelName;
    private TextView hotelAddress;
    private TextView hotelDescription;
    private TextView foodOffer;
    private TextView facilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        hotelName = (TextView)  findViewById(R.id.hd_hotel_name);
        hotelAddress = (TextView)  findViewById(R.id.hd_hotel_address);
        hotelDescription = (TextView)  findViewById(R.id.hd_hotel_description);
        foodOffer = (TextView) findViewById(R.id.hd_food_offer);
        facilities = (TextView) findViewById(R.id.hd_hotel_facilities);
        setupMVP();
        mPresenter.onStartup();

    }

    private void setupMVP() {
        mPresenter = new HotelDetailPresenter(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel_list, menu);
        TextView viewDepartureDate = (TextView) findViewById(R.id.nav_header_user_name);
        viewDepartureDate.setText(Storage.getInstance().getLoginData().getLogin());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void prepareHotelData(HotelDetail hotelDetail) {
        hotelName.setText(hotelDetail.getName());
        hotelAddress.setText(hotelDetail.getAddress());
        hotelDescription.setText(Html.fromHtml(hotelDetail.getDescription()));
        String tmpString = "";

        for (FoodOffer fo: hotelDetail.getFoodOffer()) {
            tmpString = new StringBuilder(tmpString).append("&#8226;").append(fo.getName()).append("<br/>").toString();
        }

        foodOffer.setText(Html.fromHtml(tmpString));

        tmpString = "";
        for (HotelFacilities fo: hotelDetail.getHotelFacilities()) {
            tmpString = new StringBuilder(tmpString).append("&#8226;").append(fo.getName()).append("<br/>").toString();
        }
        facilities.setText(Html.fromHtml(tmpString));
    }
}

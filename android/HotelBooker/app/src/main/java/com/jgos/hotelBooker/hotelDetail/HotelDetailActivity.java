package com.jgos.hotelBooker.hotelDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelDetail;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.FoodOffer;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.HotelFacilities;
import com.jgos.hotelBooker.data.serverEntity.room.Room;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailPresenterOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailViewOps;
import com.jgos.hotelBooker.storage.Storage;


public class HotelDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HotelDetailViewOps {

    private HotelDetailPresenterOps mPresenter;

    private TextView hotelName;
    private TextView hotelAddress;
    private TextView hotelDescription;
    private TextView foodOffer;
    private TextView facilities;

    private TextView roomNAme;
    private TextView roomPrice;
    private TextView roomCount;
    private TextView roomDescription;
    private long roomId;
    private MaterialDialog progressDialog;

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

        hotelName = (TextView) findViewById(R.id.hd_hotel_name);
        hotelAddress = (TextView) findViewById(R.id.hd_hotel_address);
        hotelDescription = (TextView) findViewById(R.id.hd_hotel_description);
        foodOffer = (TextView) findViewById(R.id.hd_food_offer);
        facilities = (TextView) findViewById(R.id.hd_hotel_facilities);

        roomNAme = (TextView) findViewById(R.id.hd_room_name);
        roomPrice = (TextView) findViewById(R.id.hd_price);
        roomCount = (TextView) findViewById(R.id.hd_no_of_ppl);
        roomDescription = (TextView) findViewById(R.id.hd_room_description);

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

        for (FoodOffer fo : hotelDetail.getFoodOffer()) {
            tmpString = new StringBuilder(tmpString).append("&#8226;").append(fo.getName()).append("<br/><br/>").toString();
        }
        tmpString = tmpString.substring(0, tmpString.length() - 8);
        foodOffer.setText(Html.fromHtml(tmpString));

        tmpString = "";
        for (HotelFacilities fo : hotelDetail.getHotelFacilities()) {
            tmpString = new StringBuilder(tmpString).append("&#8226;").append(fo.getName()).append("<br/><br/>").toString();
        }
        tmpString = tmpString.substring(0, tmpString.length() - 8);

        facilities.setText(Html.fromHtml(tmpString));
    }

    @Override
    public void prepareRoomData(Room room) {
        this.roomId = room.getId();
        roomNAme.setText(room.getName());
        roomPrice.setText(String.valueOf(room.getPrice()));
        String[] coName = getResources().getStringArray(R.array.numerOfPeople);
        roomCount.setText(coName[room.getSize()]);
        roomDescription.setText(Html.fromHtml(room.getDescription()));
    }

    @Override
    public void showSnackBar() {
        final ScrollView scrollView = (ScrollView) findViewById(R.id
                .hotel_content_view);
        Snackbar bar = Snackbar.make(scrollView, R.string.reservation_button, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.reservation, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.reservation(roomId);
                    }
                });

        bar.show();
    }

    @Override
    public void showConfirmDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.title_dialog_reservation)
                .content(R.string.content_dialog_reservation)
                .positiveText(R.string.longer_positive)
                .negativeText(R.string.negative)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.confirmReservation();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.rejectReservation();
                    }
                })
                .show();
    }

    @Override
    public void showAlertDialog(String s, boolean b) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.error)
                .content(s)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.please_wait)
                .content(R.string.process_request)
                .progress(true, 0)
                .show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }
}

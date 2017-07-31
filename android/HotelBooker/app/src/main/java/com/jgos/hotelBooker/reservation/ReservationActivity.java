package com.jgos.hotelBooker.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationData;
import com.jgos.hotelBooker.filter.FilterActivity;
import com.jgos.hotelBooker.reservation.interfaces.ReservationPresenterOps;
import com.jgos.hotelBooker.reservation.interfaces.ReservationViewOps;
import com.jgos.hotelBooker.storage.Storage;

import java.util.ArrayList;


public class ReservationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ReservationViewOps {

    private ReservationPresenterOps mPresenter;
    private MaterialDialog progressDialog;
    private ListView reservationListView ;
    private TextView reservationTextView;
    ReservationArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
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
        reservationTextView = (TextView) findViewById(R.id.reservation_msg);
        reservationListView = (ListView) findViewById(R.id.reservation_list_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupMVP();
        mPresenter.onStartup();

    }

    private void setupMVP() {
        mPresenter = new ReservationPresenter(this);
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

        if (id == R.id.nav_filter) {
            mPresenter.showFilterRequested();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showProgressBar() {
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.please_wait)
                .content(R.string.process_request)
                .progress(true, 0)
                .show();
    }

    @Override
    public void hideListView() {
        reservationListView.setVisibility(View.GONE);
    }

    @Override
    public void showDataNotAvailable() {
        reservationTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorDialogAndQuit(String s) {
        new MaterialDialog.Builder(this)
                .title(R.string.error)
                .content(s)
                .positiveText(R.string.ok)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void makeToast(String dzialam) {
        Toast.makeText(this,dzialam,Toast.LENGTH_LONG).show();
    }

    @Override
    public void stopProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showFilterActivity() {
        Intent myIntent = new Intent(this, FilterActivity.class);
        this.startActivity(myIntent);
    }


    @Override
    public void initListView(ArrayList<ReservationData> reservationDataArrayList) {
        adapter = new ReservationArrayAdapter(this, reservationDataArrayList);
        reservationListView.setAdapter(adapter);

        reservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                mPresenter.listItemSelect((ReservationData) parent.getAdapter().getItem(pos));
            }
        });
    }

}

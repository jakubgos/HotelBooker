package com.jgos.hotelBooker.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.data.entity.Coordinates;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.entity.Parking;
import com.jgos.hotelBooker.map.interfaces.MapPresenter;
import com.jgos.hotelBooker.map.interfaces.MapView;
import com.jgos.hotelBooker.parkingList.ParkingListViewImpl;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  MapsActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, MapView, MqttCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener{

    @Override
    public void  onInfoWindowClick (Marker marker){
        Log.d("Info", "Info pressed");
    }

    @Override
    public boolean onMarkerClick(final Marker marker){
        Log.d("Marker", "Marker pressed");
        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialog();
        Bundle bundle = new Bundle();
        bundle.putString("Parkingname", marker.getTitle());
        bundle.putLong("Id",(Long) marker.getTag());

        bottomSheetDialogFragment.setArguments(bundle);
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

        return false;
    }


    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
        private final View mContents;
        CustomInfoWindowAdapter(){
            mContents = getLayoutInflater().inflate(R.layout.marker_bubble, null);
        }

        @Override
        public View getInfoWindow(Marker marker){
            return null;
        }

        @Override
        public View getInfoContents(Marker marker){
            if (marker.getTitle() != "You"){
                render(marker, mContents);
                return mContents;
            }else{
                return null;
            }
        }

        private void render(Marker marker, View view){
            String title = marker.getTitle();
            TextView titleUI = ((TextView) view.findViewById(R.id.title_park));
            if (title != null){
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.RED),0,titleText.length(),0);
                titleUI.setText(titleText);
            }else {
                titleUI.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.frees));
            if (snippet != null ) {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, snippet.length(), 0);
                //snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
                snippetUi.setText(snippetText);
            } else {
                snippetUi.setText("");
            }
        }
    }




    private GoogleMap mMap;
    MapPresenter mPresenter;
    MarkerOptions marker;
    Map<Long,Marker> parkingMarkerMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        parkingMarkerMap = new HashMap<Long,Marker>();
        setupMVP();

        LoginData loginData = null;
        try {
            loginData = new ObjectMapper().readValue(getIntent().getStringExtra("LOGIN_DATA"), LoginData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mPresenter.onStartup(loginData);
       // List<Parking>lista_test=null;
       // initMQTT(lista_test);
    }

    private void setupMVP() {
        MapPresenter presenter = new MapPresenterImpl(this);
        mPresenter = presenter;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //todo update
        if (id == R.id.find_closest) {
            mPresenter.findClosestParking();
        }else if (id == R.id.favorite) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loadParkingListView() {
        Intent myIntent = new Intent(this, ParkingListViewImpl.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    @Override
    public void initGps() {
        Log.d("...","initGps");

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        // getting GPS status
        boolean  isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                mPresenter.reportLocation(new Coordinates(location.getLatitude(), location.getLongitude() ));
                Log.d("...","onLocationChanged " + "lat: "+location.getLatitude() + " long: "+ location.getLongitude());

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };
// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("...","no Prem");
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1
            );
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1
            );
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        Log.d("...","GPS started");
    }

    @Override
    public void createUserMarker(LatLng latLng) {

        if (marker==null) {
            marker = new MarkerOptions().position(latLng).title("You");
            marker.icon(BitmapDescriptorFactory.defaultMarker((BitmapDescriptorFactory.HUE_ORANGE)));
            mMap.addMarker(marker);
        }

    }

    @Override
    public void moveUserMarker(LatLng latLng) {
        marker.position(latLng);
    }

    @Override
    public void moveUserCamera(LatLng latLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
    }

    @Override
    public void updateParkingMarker(Parking parkingToModify) {

        Marker markerToUpdate = parkingMarkerMap.get(parkingToModify.getId());
        markerToUpdate.setIcon(BitmapDescriptorFactory.defaultMarker((determineColorMarker(parkingToModify))));
        markerToUpdate.setTitle(prepareParkingDescription(parkingToModify));
        markerToUpdate.setSnippet(prepareParkingSnippet(parkingToModify));
        Log.d("Parking to modify",prepareParkingSnippet(parkingToModify));
    }

    @Override
    public void centerCameraForParkings(List<Parking> list) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (Map.Entry<Long, Marker> entry : parkingMarkerMap.entrySet())
        {
            builder.include(entry.getValue().getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);
        mMap.animateCamera(cu);
    }

    @Override
    public void showParkingPosition(Parking parking) {
        MarkerOptions parkingMarker = new MarkerOptions().position(new LatLng(parking.getCoordinates().getLatitude(),parking.getCoordinates().getLongitude())).snippet(parking.getAvailablePlaces()+" / "+parking.getMaxCapacity()).title(parking.getName());
        parkingMarker.icon(BitmapDescriptorFactory.defaultMarker((determineColorMarker(parking))));
        parkingMarker.title(prepareParkingDescription(parking));
        parkingMarker.snippet(prepareParkingSnippet(parking));
        Log.d("Parking snippet",prepareParkingSnippet(parking));
        Marker resultMarker = mMap.addMarker(parkingMarker);
        resultMarker.setTag(parking.getId());
        parkingMarkerMap.put(parking.getId(),resultMarker);
    }

    private float determineColorMarker(Parking parking) {
        if(parking.getAvailablePlaces() > 40) {
            return BitmapDescriptorFactory.HUE_GREEN;
        }
        else if (parking.getAvailablePlaces() > 5)
        {
            return BitmapDescriptorFactory.HUE_YELLOW;
        }
        else {
            return BitmapDescriptorFactory.HUE_RED;
        }
    }

    private String prepareParkingDescription(Parking parking) {
        return parking.getName();

    }

    private String prepareParkingSnippet(Parking parking){
        return parking.getAvailablePlaces() + " / " + parking.getMaxCapacity();
    }

    @Override
    public void initMQTT(final List<Parking> list){
        Log.d("...","view initMQTT()");

        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client = new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.0.103:1883", clientId);
        try{
            MqttConnectOptions options = new MqttConnectOptions();
            options.setPassword("guest".toCharArray());
            options.setUserName("guest");
            IMqttToken login_token = client.connect(options);
            client.setCallback(this);

            login_token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("Mqtt","Login successful");
                    for (int i = 0; i<list.size(); i++){
                        Long parking_id = list.get(i).getId();
                        final String topic = "parking/"+parking_id;//tutaj lista topiców odpowiadających wszystkim parkingom
                        try{
                            IMqttToken subscribe_token = client.subscribe(topic,1);
                            subscribe_token.setActionCallback(new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {
                                    Log.d("Mqtt","Subscription successful. Subscribed to topic: "+ topic);
                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                    Log.d("Mqtt","Subscription to topic "+topic+" failed. Reason: "+exception);
                                }
                            });
                        }catch (MqttException e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d("Mqtt","Login failed. Reason: "+exception);
                }
            });

        }catch (MqttException e){
            e.printStackTrace();
        }

    }

    @Override
    public void connectionLost(Throwable cause){
        Log.d("Connection lost", "Connection lost because: "+cause);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token){
        //TODO implementation?
    }

    @Override
    public void messageArrived(String topic, MqttMessage message){
        Log.d("Message", "Topic: "+topic+" Message: "+message.toString());

        mPresenter.messageArrivedInd(message.toString());
    }
}

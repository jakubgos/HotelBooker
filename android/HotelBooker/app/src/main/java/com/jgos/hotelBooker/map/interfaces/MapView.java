package com.jgos.hotelBooker.map.interfaces;

import com.google.android.gms.maps.model.LatLng;
import com.jgos.hotelBooker.data.entity.Parking;

import java.util.List;

/**
 * Created by Bos on 2017-03-21.
 */
public interface MapView {
    void loadParkingListView();

    void initGps();

    void createUserMarker(LatLng coordinates);

    void showParkingPosition(Parking p);

    void initMQTT(List<Parking> list);

    void moveUserMarker(LatLng latLng);

    void moveUserCamera(LatLng latLng);


    void updateParkingMarker(Parking parkingToModify);

    void centerCameraForParkings(List<Parking> list);
}

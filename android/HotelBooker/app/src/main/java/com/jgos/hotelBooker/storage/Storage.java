package com.jgos.hotelBooker.storage;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.HotelOffer;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.data.serverEntity.hotel.HotelData;
import com.jgos.hotelBooker.data.serverEntity.hotel.data.ResultStatus;

/**
 * Created by Bos on 2017-05-18.
 */

public class Storage {
    private static Storage instance = null;

    private LoginData loginData;
    private HotelOffer hotelOffer;
    private HotelData selectedHotelData;

    public void setHotelOffer(HotelOffer hotelOffer) {
        this.hotelOffer = hotelOffer;
    }

    public boolean isUpdateListView() {
        return updateListView;
    }

    private boolean updateListView = false;

    public SearchRequest getSelectedSearchRequest() {
        return selectedSearchRequest;
    }

    private SearchRequest selectedSearchRequest;

    private Storage() {
        this.hotelOffer = new HotelOffer(ResultStatus.NOT_INITIALED);
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public HotelData getSelectedHotelData() {
        return selectedHotelData;
    }

    public void setSelectedHotelData(HotelData selectedHotelData) {
        this.selectedHotelData = selectedHotelData;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public void save(HotelOffer hotelOffer) {
        this.hotelOffer = hotelOffer;
    }

    public HotelOffer getHotelOffer() {
        return hotelOffer;
    }

    public void setSelectedSearchRequest(SearchRequest selectedSearchRequest) {
        this.selectedSearchRequest = selectedSearchRequest;
    }

    public void setUpdateListView(boolean b) {
        this.updateListView = b;
    }
}

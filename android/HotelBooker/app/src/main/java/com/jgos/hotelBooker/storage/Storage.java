package com.jgos.hotelBooker.storage;

import com.jgos.hotelBooker.data.entity.LoginData;

/**
 * Created by Bos on 2017-05-18.
 */

public class Storage {
    private static Storage instance=null;

    private LoginData loginData;


    private Storage()
    {}

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }
}

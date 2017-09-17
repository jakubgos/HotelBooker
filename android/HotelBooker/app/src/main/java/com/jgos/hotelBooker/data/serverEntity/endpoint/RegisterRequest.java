package com.jgos.hotelBooker.data.serverEntity.endpoint;

/**
 * Created by Bos on 2017-09-17.
 */


public class RegisterRequest {

    String user;
    String password;

    public RegisterRequest() {
    }

    public RegisterRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

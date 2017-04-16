package com.jgos.hotelBooker.login.entity;

/**
 * Created by Bos on 2017-03-04.
 */

public class LoginReqParam {
    String email;
    String password;
    public LoginReqParam(String email, String password) {
        this.email=email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

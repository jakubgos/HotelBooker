package com.jgos.hotelbooker.entity.endpoint;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Bos on 2017-09-17.
 */
public class RegisterRequest {
    @NotNull
    @NotEmpty
    String user;
    @NotNull
    @NotEmpty
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

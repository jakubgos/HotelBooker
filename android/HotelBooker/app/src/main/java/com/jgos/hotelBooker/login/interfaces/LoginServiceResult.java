package com.jgos.hotelBooker.login.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;

/**
 * Created by Bos on 2017-03-04.
 */

public interface LoginServiceResult {

    void loginSuccess(LoginData s);

    void loginFailed(LoginData loginData);
}

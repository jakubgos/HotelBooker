package com.jgos.hotelBooker.login.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;

/**
 * Created by Bos on 2017-03-04.
 */

public interface LoginServiceLoginResult {

    void loginSuccess(LoginData s);

    void loginFailed(LoginData loginData);
}

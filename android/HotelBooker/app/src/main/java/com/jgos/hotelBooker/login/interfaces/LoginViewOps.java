package com.jgos.hotelBooker.login.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.login.entity.Result;

/**
 * Created by Bos on 2017-03-04.
 */
public interface LoginViewOps {

    void showProgress(boolean b);

    void showLoginError(Result msg, String message);

    void resetLoginErrors();

    void showMapActivity(LoginData s);
}


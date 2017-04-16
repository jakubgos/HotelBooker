package com.jgos.hotelBooker.login.interfaces;

import com.jgos.hotelBooker.login.entity.LoginReqParam;

/**
 * Created by Bos on 2017-03-04.
 */
public interface LoginPresenterOps {
    void attemptLogin(LoginReqParam loginReqParam);

    void onResume();

    void onStartup();
}

package com.jgos.hotelBooker.login.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.entity.Result;

/**
 * Created by Bos on 2017-03-04.
 */
public interface LoginModelPresenterOps {
    void loginSuccess(LoginData s);

    void loginFailed(LoginData loginInvalid);

    void validateLoginParamFailed(Result loginInvalid);

    void validateLoginParamSuccess(LoginReqParam loginReqParam);
}

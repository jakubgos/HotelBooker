package com.jgos.hotelBooker.data.interfaces;

import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.serverEntity.endpoint.RateRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.RegisterRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.SearchRequest;
import com.jgos.hotelBooker.filter.interfaces.LoginServiceCityListResult;
import com.jgos.hotelBooker.filter.interfaces.SearchRequestResult;
import com.jgos.hotelBooker.hotelList.interfaces.ReservationRequestResult;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.interfaces.LoginServiceLoginResult;
import com.jgos.hotelBooker.login.interfaces.LoginServiceRegisterResult;
import com.jgos.hotelBooker.reservation.interfaces.RateRequestResult;
import com.jgos.hotelBooker.reservation.interfaces.UserReservationResult;

/**
 * Created by Bos on 2017-03-04.
 */
public interface NetworkService {
    void login(LoginReqParam loginReqParam, LoginServiceLoginResult loginServiceResult);

    void getCityList(LoginData loginData, LoginServiceCityListResult loginServiceCityListResult);

    void searchRequest(SearchRequest searchRequest, LoginData loginData, SearchRequestResult searchRequestResult);

    void reservationRequest(ReservationRequest reservationRequest, LoginData loginData, ReservationRequestResult reservationRequestResult);

    void downloadUserReservation(LoginData loginData, UserReservationResult userReservationResult);


    void register(RegisterRequest registerRequest, LoginServiceRegisterResult loginServiceRegisterResult);

    void rateRequest(LoginData loginData, RateRequest rateRequest, RateRequestResult rateRequestResult);
}

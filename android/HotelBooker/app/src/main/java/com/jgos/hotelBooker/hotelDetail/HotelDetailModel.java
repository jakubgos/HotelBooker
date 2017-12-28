package com.jgos.hotelBooker.hotelDetail;

import android.graphics.Bitmap;

import com.jgos.hotelBooker.data.NetworkServiceImpl;
import com.jgos.hotelBooker.data.entity.LoginData;
import com.jgos.hotelBooker.data.interfaces.NetworkService;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationRequest;
import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationResponse;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailModelOps;
import com.jgos.hotelBooker.hotelDetail.interfaces.HotelDetailPresenterOps;
import com.jgos.hotelBooker.hotelList.interfaces.ReservationRequestResult;
import com.jgos.hotelBooker.login.interfaces.getPictureResult;

/**
 * Created by Bos on 2017-06-18.
 */

class HotelDetailModel implements HotelDetailModelOps {
    private final HotelDetailPresenterOps hoteDetailPresenter;

    //todo remove:, replace with callback
    private final NetworkService networkService;

    public HotelDetailModel(HotelDetailPresenterOps hotelDetailPresenterOps, NetworkServiceImpl networkService) {
        this.hoteDetailPresenter = hotelDetailPresenterOps;
        this.networkService = networkService;
    }

    @Override
    public void reserveRoom(final ReservationRequest reservationRequest, LoginData loginData) {
        networkService.reservationRequest(reservationRequest, loginData, new ReservationRequestResult() {
            @Override
            public void reservationRequestFailure(String s) {
                hoteDetailPresenter.reservationRequestFailure(s);
            }

            @Override
            public void reservationRequestResult(ReservationResponse reservationResponse) {
                hoteDetailPresenter.reservationRequestResult(reservationResponse);
            }

            @Override
            public void reservationRequestReject(ReservationResponse reservationResponse) {
                hoteDetailPresenter.reservationRequestReject(reservationResponse);
            }
        });
    }

    @Override
    public void getPicture(String picturePath) {
        networkService.getPicture(picturePath, new getPictureResult(){
            @Override
            public void getPictureResultOk(Bitmap bitmap) {
                hoteDetailPresenter.getPictureResultOk( bitmap);
            }

            @Override
            public void getPictureResultNOk() {
                hoteDetailPresenter.getPictureResultNOk();
            }
        });
    }
}

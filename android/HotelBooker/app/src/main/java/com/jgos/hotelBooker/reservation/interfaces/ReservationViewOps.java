package com.jgos.hotelBooker.reservation.interfaces;

import com.jgos.hotelBooker.data.serverEntity.endpoint.ReservationData;

import java.util.ArrayList;

/**
 * Created by Bos on 2017-06-18.
 */

public interface ReservationViewOps {
    void showProgressBar();

    void hideListView();

    void showDataNotAvailable();

    void initListView(ArrayList<ReservationData> reservationDataArrayList);

    void showErrorDialogAndQuit(String s);

    void makeToast(String dzialam);

    void stopProgressBar();

    void showFilterActivity();

    void showRateDialog(ReservationData item);
}

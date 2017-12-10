package com.jgos.hotelbooker.service;


import com.jgos.hotelbooker.entity.endpoint.RateRequest;
import com.jgos.hotelbooker.entity.hotel.Feedback;
import com.jgos.hotelbooker.entity.hotel.Hotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;
import com.jgos.hotelbooker.entity.user.Reservation;
import com.jgos.hotelbooker.repository.FeedbackRepository;
import com.jgos.hotelbooker.repository.HotelDetailRepository;
import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ReservationRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    @Transactional(rollbackFor={Exception.class})
    public ResultStatus rate(RateRequest rateRequest) {
        log.debug("rate invoked with data: "+ rateRequest );

        //check if rate possible
        Reservation reservation = reservationRepository.findOne(rateRequest.getReservationId());

        if(reservation.getFeedback().getValid())
        {
            log.debug("rate Reservation already rated "+ rateRequest + " " + reservation );
            return ResultStatus.RATE_ERROR;
        }
        //create new rating:
        Feedback feedback = new Feedback(rateRequest.getValue(), true);

        Hotel hotel = hotelRepository.findByOwner(reservation.getOwner());
        hotel.getHotelDetail().addFeedback(feedback);
        reservation.setFeedback(feedback);

        feedbackRepository.save(feedback);
        hotelRepository.save(hotel);
        reservationRepository.save(reservation);

        return ResultStatus.OK;
    }
}

package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.entity.endpoint.RateRequest;
import com.jgos.hotelbooker.entity.hotel.data.ResultStatus;

public interface FeedbackService {

    public ResultStatus rate (RateRequest rateRequest);
}

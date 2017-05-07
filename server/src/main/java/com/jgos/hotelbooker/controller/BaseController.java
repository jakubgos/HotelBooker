package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.exceptions.ForbiddenException;
import com.jgos.hotelbooker.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bos on 2017-04-16.
 */

public class BaseController {

    /*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> notFoundExceptionHandler(NotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public Map<String, String> forbiddenException(ForbiddenException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return response;
    }
    */
}

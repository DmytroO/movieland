package com.doliinyk.movieland.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
public class BadRequest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({IllegalArgumentException.class,NoSuchFieldError.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @SuppressWarnings("unused")
    void handleBadRequests(Exception e) {
        logger.error("Exception:{}", e);
    }
}

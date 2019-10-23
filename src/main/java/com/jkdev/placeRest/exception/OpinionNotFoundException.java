package com.jkdev.placeRest.exception;

public class OpinionNotFoundException extends RuntimeException {
    public OpinionNotFoundException() {
    }

    public OpinionNotFoundException(String message) {
        super(message);
    }

    public OpinionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpinionNotFoundException(Throwable cause) {
        super(cause);
    }

    public OpinionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
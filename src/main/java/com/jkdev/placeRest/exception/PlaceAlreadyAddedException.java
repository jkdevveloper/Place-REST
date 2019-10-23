package com.jkdev.placeRest.exception;

public class PlaceAlreadyAddedException extends RuntimeException {

    public PlaceAlreadyAddedException() {
    }

    public PlaceAlreadyAddedException(String message) {
        super(message);
    }

    public PlaceAlreadyAddedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceAlreadyAddedException(Throwable cause) {
        super(cause);
    }

    public PlaceAlreadyAddedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
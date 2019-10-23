package com.jkdev.placeRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlaceRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PlaceErrorResponse> handleException(PlaceNotFoundException exc) {

        PlaceErrorResponse error = new PlaceErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlaceErrorResponse> handleException(OpinionNotFoundException exc) {
        PlaceErrorResponse error = new PlaceErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlaceErrorResponse> handleException(PlaceAlreadyAddedException exc) {
        PlaceErrorResponse error = new PlaceErrorResponse(
                HttpStatus.CONFLICT.value(),
                exc.getMessage(),
                System.currentTimeMillis());


        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<PlaceErrorResponse> handleException(Exception exc) {


        PlaceErrorResponse error = new PlaceErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());


        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

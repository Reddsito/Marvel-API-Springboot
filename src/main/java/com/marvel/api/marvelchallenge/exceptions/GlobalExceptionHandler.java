package com.marvel.api.marvelchallenge.exceptions;

import com.marvel.api.marvelchallenge.persistence.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception exception,
                                                          WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handlerException(RuntimeException exception,
                                                          WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.marvel.api.marvelchallenge.exceptions;

public class ApiErrorMessage extends RuntimeException{
    public ApiErrorMessage() {
        super();
    }

    public ApiErrorMessage(String message) {
        super(message);
    }

    public ApiErrorMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiErrorMessage(Throwable cause) {
        super(cause);
    }
}

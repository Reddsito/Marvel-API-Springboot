package com.marvel.api.marvelchallenge.persistence.dto;

import java.util.Date;

public class ErrorResponse {
    private Date date = new Date();
    private String response;
    private String url;

    public ErrorResponse(String response, String url) {
        this.response = response;
        this.url = url.replace("uri=", "");
    }
}

package com.marvel.api.marvelchallenge.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class ApiErrorDto {
    private String message;
    private String backendMessage;
    private String method;
    private String url;

    public ApiErrorDto(String message, String backendMessage, String method, String url) {
        this.backendMessage = backendMessage;
        this.url = url.replace("uri=", "");
        this.message = message;
        this.method = method;
    }
}

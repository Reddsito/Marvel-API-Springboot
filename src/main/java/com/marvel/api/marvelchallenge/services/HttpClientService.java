package com.marvel.api.marvelchallenge.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface HttpClientService {

     <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> responseType);
    <T,R> T doPost(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);
    <T,R> T doPut(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);
    <T> T doDelete(String endpoint, Map<String, String> queryParams, Class<T> responseType);

}

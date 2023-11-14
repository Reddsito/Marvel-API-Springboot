package com.marvel.api.marvelchallenge.services;

import com.marvel.api.marvelchallenge.persistence.dto.security.LoginRequest;
import com.marvel.api.marvelchallenge.persistence.dto.security.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    public LoginResponse authenticate(LoginRequest loginRequest);

    void logout() throws Exception;
}

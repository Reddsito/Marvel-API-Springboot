package com.marvel.api.marvelchallenge.controllers;

import com.marvel.api.marvelchallenge.persistence.dto.security.LoginRequest;
import com.marvel.api.marvelchallenge.persistence.dto.security.LoginResponse;
import com.marvel.api.marvelchallenge.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody @Valid LoginRequest loginRequest)
    {
    return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }

    @PostMapping("/logout")
    public void logout() {
        authenticationService.logout();
    }

}

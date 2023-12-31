package com.marvel.api.marvelchallenge.controllers;

import com.marvel.api.marvelchallenge.persistence.dto.security.LoginRequest;
import com.marvel.api.marvelchallenge.persistence.dto.security.LoginResponse;
import com.marvel.api.marvelchallenge.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthenticationService authenticationService;

    @PreAuthorize("permitAll()")
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody @Valid LoginRequest loginRequest)
    {
    return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/logout")
    public void logout() throws Exception {
        authenticationService.logout();
    }

}

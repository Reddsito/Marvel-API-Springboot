package com.marvel.api.marvelchallenge.security.validator;

import com.marvel.api.marvelchallenge.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("interactionLogValidator")
@RequiredArgsConstructor
public class UserInteractionLogValidator {

    private final AuthenticationService authenticationService;

    public boolean validate(String username) {
        String userLoggedIn = authenticationService.getUserLoggedIn().getUsername();
        return userLoggedIn.equals(username);
    }

}

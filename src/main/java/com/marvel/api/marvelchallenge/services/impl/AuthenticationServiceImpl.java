package com.marvel.api.marvelchallenge.services.impl;

import com.marvel.api.marvelchallenge.persistence.dto.security.LoginRequest;
import com.marvel.api.marvelchallenge.persistence.dto.security.LoginResponse;
import com.marvel.api.marvelchallenge.persistence.entity.User;
import com.marvel.api.marvelchallenge.services.AuthenticationService;
import com.marvel.api.marvelchallenge.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final HttpSecurity http;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {

        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.username());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, loginRequest.password(), user.getAuthorities()
        );
        authenticationManager.authenticate(authentication);

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new LoginResponse(jwt);
    }

    @Override
    public void logout() {
       try {
           http.logout(logoutCOnfig -> {
               logoutCOnfig.deleteCookies("JSESSIONID")
                       .clearAuthentication(true)
                       .invalidateHttpSession(true);
           });
       } catch (Exception e) {
            throw new RuntimeException(e);
       }
    }

    private Map<String, Object> generateExtraClaims(UserDetails user) {
        Map<String, Object> extraClaims = new HashMap<>();

        String roleName =( (User) user).getRole().getName().name();

        extraClaims.put("role", roleName);
        extraClaims.put("authorities", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
        );

        return extraClaims;
    }
}

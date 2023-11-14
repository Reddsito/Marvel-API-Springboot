package com.marvel.api.marvelchallenge.interceptors;

import com.marvel.api.marvelchallenge.exceptions.ApiErrorMessage;
import com.marvel.api.marvelchallenge.persistence.entity.UserInteractionLog;
import com.marvel.api.marvelchallenge.persistence.repository.UserInteractionLogRepository;
import com.marvel.api.marvelchallenge.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserInteractionInterceptor implements HandlerInterceptor {


    private final UserInteractionLogRepository userInteractionLogRepository;
    private final AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        if(request.getRequestURI().startsWith("/api/v1/characters") ||  request.getRequestURI().startsWith("/api/v1/comics")   ) {

            System.out.println("Interceptor: " + this.getClass().getName());

            UserDetails user = authenticationService.getUserLoggedIn();
            UserInteractionLog userLog = UserInteractionLog
                    .builder()
                    .httpMethod(request.getMethod())
                    .url(request.getRequestURL().toString())
                    .username(user.getUsername())
                    .remoteAddress(request.getRemoteAddr())
                    .timestamp(LocalDateTime.now())
                    .build();

            try {
                userInteractionLogRepository.save(userLog);
                return true;
            } catch (Exception ex) {
                throw new ApiErrorMessage("No se logró guardar el log correctamente");
            }
        }

        return true;
    }
}

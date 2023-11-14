package com.marvel.api.marvelchallenge.exceptions;

import com.marvel.api.marvelchallenge.persistence.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handlerGeneralExceptions(
            Exception e,
            HttpServletRequest request,
            WebRequest webRequest
    ) {

        if( e instanceof HttpClientErrorException) {
            return this.handlerHttpClientErrorException( (HttpClientErrorException) e, request, webRequest);
        } else if ( e instanceof AccessDeniedException) {
            return this.handleAccessDeniedException( (AccessDeniedException) e, request,webRequest);
        } else if ( e instanceof AuthenticationCredentialsNotFoundException) {
            return this.handleAuthenticationCredentialsNotFoundException( (AuthenticationCredentialsNotFoundException) e, request,webRequest);
        } else {
            return this.handleGenericException(e, request, webRequest);
        }

    }

    private ResponseEntity<ApiErrorDto> handleGenericException(Exception e, HttpServletRequest request, WebRequest webRequest) {
        ApiErrorDto error = ApiErrorDto
                .builder()
                .message("Error inesperado, vuelva a intentarlo")
                .backendMessage(e.getMessage())
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(500).body(error);
    }

    private ResponseEntity<ApiErrorDto> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e, HttpServletRequest request, WebRequest webRequest) {
        ApiErrorDto error = ApiErrorDto
                .builder()
                .message(e.getMessage())
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    private ResponseEntity<ApiErrorDto> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request, WebRequest webRequest) {
        ApiErrorDto error = ApiErrorDto
                .builder()
                .message(e.getMessage())
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    private ResponseEntity<ApiErrorDto> handlerHttpClientErrorException(HttpClientErrorException e, HttpServletRequest request, WebRequest webRequest) {


        ApiErrorDto error = ApiErrorDto
                .builder()
                .backendMessage(e.getMessage())
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();

        if ( e instanceof  HttpClientErrorException.Forbidden ) {
            error.setMessage("No tienes acceso a este recurso");
        } else if ( e instanceof  HttpClientErrorException.Unauthorized ) {
            error.setMessage("No haz iniciado sessión para acceder a este recurso");
        } else if ( e instanceof  HttpClientErrorException.NotFound ) {
            error.setMessage("Recurso no encontrado");
        } else if ( e instanceof  HttpClientErrorException.Conflict ) {
            error.setMessage("Hubo un error en el procesamiento de la petición");
        } else {
            error.setMessage("Error inesperado al realizar consulta");
        }
        return ResponseEntity.status(e.getStatusCode()).body(error);
    }

}

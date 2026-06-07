package com.adrianEjemploSpringRest.apirest_productos.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(
            ResponseStatusException ex
    ) {

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getReason());

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(
            RuntimeException ex
    ) {

        return ResponseEntity
                .internalServerError()
                .body(ex.getMessage());

    }
}
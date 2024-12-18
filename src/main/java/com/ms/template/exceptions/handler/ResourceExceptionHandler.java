package com.ms.template.exceptions.handler;

import com.ms.template.exceptions.ApiException;
import com.ms.template.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<StandardError> handler(ApiException exception) {
        var error = StandardError.builder()
                .message(exception.getMessage())
                .code(exception.getCode())
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.valueOf(exception.getCode()));
    }
}

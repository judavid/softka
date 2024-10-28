package com.accountmovementservice.accountmovementservice.exceptions;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(BalanceNotAvailableException.class)
    public final ResponseEntity<Object> handleAllExceptions(BalanceNotAvailableException ex, WebRequest request) {
        return new ResponseEntity<>(ex, HttpStatus.PRECONDITION_FAILED);
    }
}

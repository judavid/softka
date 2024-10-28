package com.accountmovementservice.accountmovementservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class BalanceNotAvailableException extends RuntimeException {
    public BalanceNotAvailableException(String message) {
        super(message);
    }
}

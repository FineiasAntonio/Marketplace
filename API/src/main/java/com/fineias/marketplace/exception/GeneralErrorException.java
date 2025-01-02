package com.fineias.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralErrorException extends RuntimeException {
    public GeneralErrorException() {
        super("An error has occurred");
    }
}

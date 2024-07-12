package com.bitgo.cryptoNotification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not found")

public class BadRequestException extends Exception{
    public BadRequestException(String msg){
        super(msg);
    }
}

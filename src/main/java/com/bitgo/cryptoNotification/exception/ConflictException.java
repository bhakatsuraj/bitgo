package com.bitgo.cryptoNotification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Not found")

public class ConflictException extends Exception{

    public ConflictException(String msg){
        super(msg);
    }
}

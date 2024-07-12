package com.bitgo.cryptoNotification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotfoundException extends Exception{

    public NotfoundException(String msg){
        super(msg);
    }
}

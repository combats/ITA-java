package com.softserveinc.ita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User is empty")
public class EmptyUserException extends UserException {

    public EmptyUserException(String message){
        super(message);
    }

    public EmptyUserException(){
    }
}

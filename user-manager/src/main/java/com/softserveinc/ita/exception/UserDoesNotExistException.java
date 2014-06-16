package com.softserveinc.ita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User does not exist")
public class UserDoesNotExistException extends UserException {

    public UserDoesNotExistException(String message){
        super(message);
    }

    public UserDoesNotExistException(){
    }
}

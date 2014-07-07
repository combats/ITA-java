package com.softserveinc.ita.exception.impl;
import com.softserveinc.ita.exception.GroupException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Group status does not exist")
public class WrongGroupStatusException extends GroupException {

    public WrongGroupStatusException(){
    }

    public WrongGroupStatusException(String message){
        super(message);
    }
}

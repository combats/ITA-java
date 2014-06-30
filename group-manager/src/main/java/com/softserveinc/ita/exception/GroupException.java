package com.softserveinc.ita.exception;

public class GroupException extends RuntimeException {

    public GroupException(){

    }

    public GroupException(String message){
        super(message);
    }
}

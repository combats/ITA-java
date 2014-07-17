package com.softserveinc.ita.exception;

/**
 * Custom Exception for checking routing problems in request paths in RepositoryController

 */
public class RoutingException extends Exception{
    public RoutingException() {
    }

    public RoutingException(String message) {
        super(message);
    }
}

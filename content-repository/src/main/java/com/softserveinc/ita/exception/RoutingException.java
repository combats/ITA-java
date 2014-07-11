package com.softserveinc.ita.exception;

/**
 * Custom Exception for Controller RoutingHandling handling (converting),
 * is needed when we need to throw an exception or some unexpected exception occurs
 */
public class RoutingException extends Exception{
    public RoutingException() {
    }

    public RoutingException(String message) {
        super(message);
    }
}

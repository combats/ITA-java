package com.softserveinc.ita.exeption;

/**
 * Created by Rossoha on 10.06.2014.
 */
public class InvalidDateArgumentException extends InvalidArgumentExertion{
    public InvalidDateArgumentException(String message) {
        super(message);
    }

    public InvalidDateArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}

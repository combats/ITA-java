package com.softserveinc.ita.exeption;

/**
 * Created by Rossoha on 10.06.2014.
 */
public class InvalidArgumentExertion extends Exception{
    public InvalidArgumentExertion(String message) {
        super(message);
    }

    public InvalidArgumentExertion(String message, Throwable cause) {
        super(message, cause);
    }
}

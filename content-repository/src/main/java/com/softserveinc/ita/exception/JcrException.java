package com.softserveinc.ita.exception;

/**
 * Custom Exception for Jackrabbit RepositoryException handling (converting),
 * is needed when we need to throw an exception or some unexpected exception occurs
 */
public class JcrException extends Exception{
    public JcrException() {
    }

    public JcrException(String message) {
        super(message);
    }
}

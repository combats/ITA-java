package com.softserveinc.ita.exception;

/**
 * Custom Exception for Jackrabbit RepositoryException handling (converting),
 * is needed when some unexpected exception occurs in JackRabbit implementation
 */
public class JcrException extends Exception{
    public JcrException() {
    }

    public JcrException(String message) {
        super(message);
    }
}

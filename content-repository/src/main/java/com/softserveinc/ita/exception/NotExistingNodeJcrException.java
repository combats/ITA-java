package com.softserveinc.ita.exception;

/**
 * Custom Exception for handling bad requests (when requested Node doesn't exist),
 * is needed when we need to throw an exception
 */
public class NotExistingNodeJcrException extends JcrException {
    public NotExistingNodeJcrException() {
    }

    public NotExistingNodeJcrException(String message) {
        super(message);
    }
}

package com.softserveinc.ita.exception;

/**
 * Custom Exception for Base 64 validations in RepositoryController
 */
public class Base64ValidationException extends Exception {
    public Base64ValidationException() {
    }

    public Base64ValidationException(String message) {
        super(message);
    }
}

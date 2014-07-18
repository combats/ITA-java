package com.softserveinc.ita.exception;

/**
 * Used for File validation in RepositoryController
 */
public class EmptyFileException extends Exception {
    public EmptyFileException() {
    }

    public EmptyFileException(String message) {
        super(message);
    }
}

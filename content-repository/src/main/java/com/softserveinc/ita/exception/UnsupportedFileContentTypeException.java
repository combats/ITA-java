package com.softserveinc.ita.exception;

/**
 * Used for File validation in RepositoryController
 */
public class UnsupportedFileContentTypeException extends Exception {
    public UnsupportedFileContentTypeException() {
    }

    public UnsupportedFileContentTypeException(String message) {
        super(message);
    }
}

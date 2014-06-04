package com.softserveinc.ita.servise.exeption;


public class HttpRequestException extends Exception {

    public HttpRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpRequestException(String msg) {
        super(msg);
    }
}

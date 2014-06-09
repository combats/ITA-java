package com.softserveinc.ita.servise.exeption;


public class HttpHandlerRequestException extends Exception {

    public HttpHandlerRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpHandlerRequestException(String msg) {
        super(msg);

    }
}

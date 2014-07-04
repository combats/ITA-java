package com.softserveinc.ita.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class AppointmentNotFoundException extends Exception {

    public AppointmentNotFoundException() {
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}

package com.softserveinc.ita.dao.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 18.06.14
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class InterviewNotFoundException extends InterviewException {

    public InterviewNotFoundException() {
    }

    public InterviewNotFoundException(String message) {
        super(message);
    }
}

package com.softserveinc.ita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 17.06.14
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "APPOINTMENT NOT FOUND")
public class AppointmentNotFoundException extends AppointmentException {
}

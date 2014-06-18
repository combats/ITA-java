package com.softserveinc.ita.dao.exceptions;

/**
 * Created by mskryntc on 09.06.2014.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AppointmentException extends Exception {
}
package com.softserveinc.ita.dao.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mskryntc on 09.06.2014.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "APPOINTMENT NOT FOUND")
public class AppointmentNotFoundException extends AppointmentException {
}

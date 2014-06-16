package com.softserveinc.ita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "APPOINTMENT NOT FOUND")
public class AppointmentNotFoundException extends AppointmentException {
}

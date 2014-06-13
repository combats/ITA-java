package com.softserveinc.ita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Applicant does not exist")
public class ApplicantDoesNotExistException extends ApplicantException {
    public ApplicantDoesNotExistException(){}
}

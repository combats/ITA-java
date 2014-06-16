package com.softserveinc.ita.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Anton on 07.06.2014.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Invalid user ID")
public class InvalidUserIDException extends Exception{

}

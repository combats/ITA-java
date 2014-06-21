package com.softserveinc.ita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Invalid user ID")
public class InvalidUserIDException extends Exception{

}

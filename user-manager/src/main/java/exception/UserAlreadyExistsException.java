package exception;

/**
 * Created by akravctc on 05.06.2014.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "User already exists")
public class UserAlreadyExistsException extends Exception {

}

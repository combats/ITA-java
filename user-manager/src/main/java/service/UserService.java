package service;

import com.softserveinc.ita.entity.User;
import exception.UserAlreadyExistsException;

/**
 * Created by Anton on 04.06.2014.
 */

public interface UserService {
    User postNewUser(User user) throws UserAlreadyExistsException;
}

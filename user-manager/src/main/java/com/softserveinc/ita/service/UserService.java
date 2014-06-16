package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;

public interface UserService {
    User editUser(User user) throws UserDoesNotExistException, EmptyUserException;
}

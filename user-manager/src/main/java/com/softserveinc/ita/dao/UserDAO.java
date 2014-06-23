package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;

public interface UserDAO {
    User postNewUser (User user) throws UserAlreadyExistsException;
}

package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;

public interface UserService {
    User getUserByID (String UserID) throws InvalidUserIDException;
}

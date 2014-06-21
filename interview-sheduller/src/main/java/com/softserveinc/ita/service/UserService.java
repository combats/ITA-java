package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exceptions.InvalidUserIDException;

public interface UserService {
    public boolean userExists(String userId);
    User getUserByID (String UserID) throws InvalidUserIDException;
}

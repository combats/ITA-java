package com.softserveinc.ita.service;


import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;

public interface UserService {
    User postNewUser(User user) throws UserAlreadyExistsException;
}

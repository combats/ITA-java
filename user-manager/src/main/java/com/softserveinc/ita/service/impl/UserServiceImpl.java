package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User postNewUser(User user) throws UserAlreadyExistsException {
        userDAO.postNewUser(user);
        return user;
    }
}

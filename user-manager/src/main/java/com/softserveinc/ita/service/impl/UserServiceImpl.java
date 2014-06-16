package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public User getUserByID(String UserID) throws InvalidUserIDException
    {
       if (userDao.getUserByID(UserID) == null) throw new InvalidUserIDException();
       return userDao.getUserByID(UserID);
    }
}

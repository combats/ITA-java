package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMockService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException {
        userDao.deleteUser(userID);
    }
}

package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMockService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void deleteUser(String userID) throws Exception {
        userDao.deleteUser(userID);
    }
}

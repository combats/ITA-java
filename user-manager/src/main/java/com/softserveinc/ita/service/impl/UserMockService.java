package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserMockService implements UserService {
    @Autowired
    private UserDao userDao;

    public ArrayList<String> getAllUsersID() {
        return userDao.getAllUsersID();
    }
}

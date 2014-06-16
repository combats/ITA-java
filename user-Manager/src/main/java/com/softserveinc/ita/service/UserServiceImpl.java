package com.softserveinc.ita.service;

import com.softserveinc.ita.DAO.UserDAO;
import com.softserveinc.ita.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDAOImpl")
    private UserDAO userDAO;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}

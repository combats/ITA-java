package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.DAO.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceMockImpl implements UserService {

    @Autowired
    private UserDAO userDAOImpl;

    @Override
    public List<User> getUsers() {
        return userDAOImpl.getUsers();
    }
}

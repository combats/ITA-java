package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.service.UserService;
import com.softserveinc.ita.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class UserMockService implements UserService {

    @Autowired
    private JsonUtil jsonUtil;

    @Override
    public User postNewUser(User user) throws UserAlreadyExistsException {
        ArrayList<User> users = new ArrayList<User>() {{
            add(new User("1"));
            add(new User("2"));
        }};
        for (User u : users) {
            if (user.equals(u))
                throw new UserAlreadyExistsException();
        }
        return user;
    }
}

package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDAOMockImpl implements UserDAO {

    @Override
    public User postNewUser(User user) throws UserAlreadyExistsException{
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

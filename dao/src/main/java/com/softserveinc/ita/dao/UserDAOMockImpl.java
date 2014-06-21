package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAOMockImpl implements UserDAO {
    @Override
    public User getUserByID(String UserID) {
        User user = null;
        ArrayList<User> users = new ArrayList<User>() {{
            add(new User("1"));
            add(new User("2"));
        }};

        for (User u : users) {
            if (u.getUserID().equals(UserID)) {
                user = u;
            }
        }
        return user;
    }
}

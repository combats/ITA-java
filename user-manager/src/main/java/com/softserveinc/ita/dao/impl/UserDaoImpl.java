package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDaoImpl implements UserDao {
    public ArrayList<String> getAllUsersID(){
        ArrayList<User> usersList = new ArrayList<User>() {{
            add(new User("1"));
            add(new User("2"));
            add(new User("3"));
        }};
        ArrayList<String> usersIDList = new ArrayList<>();
        for (User u : usersList){
            usersIDList.add(u.getUserID());
        }
        return usersIDList;
    }
}

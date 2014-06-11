package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserMockDao implements UserDao{
    private Map<String, User> db = new HashMap<>();

    public boolean alreadyContains(String userID) {
        db.put("123", new User("123"));

        if(db.containsKey(userID)){
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(String userID) throws Exception {
        if(alreadyContains(userID)){
            db.remove(userID);
        } else {
            throw new Exception("User DAOMock: User with (" + userID + ") userID not found");
        }
    }
}

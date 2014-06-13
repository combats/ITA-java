package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserMockDao implements UserDao{
    private Map<String, User> db = new HashMap<>();

    @Override
    public void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException {
        db.put("121", new User("121"));
        db.put("122", new User("122"));
        db.put("123", new User("123"));

        if(db.containsKey(userID)){
            db.remove(userID);
        } else {
            throw new UserIDNotFoundUserDaoMockException("User DAOMock: User with (" + userID + ") userID not found");
        }
    }
}
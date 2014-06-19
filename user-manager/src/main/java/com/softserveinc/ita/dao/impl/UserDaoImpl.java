package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao{
    private Map<String, User> db;

    public UserDaoImpl() {
        db = new HashMap<>();
        db.put("121", new User("121"));
        db.put("122", new User("122"));
        db.put("123", new User("123"));
    }

    @Override
    public void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException {

        if(db.containsKey(userID)){
            db.remove(userID);
        } else {
            String errorMessage = "User DAOMock: User with userID not found: ";
            throw new UserIDNotFoundUserDaoMockException(errorMessage + userID);
        }
    }
}
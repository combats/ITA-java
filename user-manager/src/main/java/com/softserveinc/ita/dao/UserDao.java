package com.softserveinc.ita.dao;

import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;

public interface UserDao {
    void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException;
}

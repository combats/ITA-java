package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.exception.UserAlreadyExistsException;

import java.util.ArrayList;

public interface UserDAO {
    User getUserByID(String UserID);

    ArrayList<String> getAllUsersID();

    void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException;

    User changeUser(User changingUser);

    User postNewUser(User user) throws UserAlreadyExistsException;

}

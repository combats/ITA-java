package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.exception.UserDoesNotExistException;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    User getUserByID(String UserID);

    ArrayList<String> getAllUsersID();

    String deleteUser(String userID) throws UserDoesNotExistException;

    User changeUser(User changingUser);

    User postNewUser(User user) throws UserAlreadyExistsException;

    List<User> getUsers();
}

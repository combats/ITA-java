package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;

public interface UserService {
    User getUserByID (String UserID) throws InvalidUserIDException;
    ArrayList<String> getAllUsersID();
    void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException;
    User editUser(User user) throws UserDoesNotExistException, EmptyUserException;
    List<User> getUsers();
}

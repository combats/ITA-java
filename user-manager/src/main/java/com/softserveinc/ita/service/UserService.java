package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import java.util.ArrayList;
import java.util.List;

import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.exception.UserAlreadyExistsException;

public interface UserService {
    User getUserByID (String UserID) throws InvalidUserIDException;
    ArrayList<String> getAllUsersID();
    String deleteUser(String userID) throws UserDoesNotExistException;
    User editUser(User user) throws UserDoesNotExistException, EmptyUserException;
    User postNewUser(User user) throws UserAlreadyExistsException;
    List<User> getUsers();
}

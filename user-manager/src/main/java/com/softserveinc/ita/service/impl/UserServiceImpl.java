package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public ArrayList<String> getAllUsersID() {
        return userDao.getAllUsersID();
    }
    @Override
    public User getUserByID(String UserID) throws InvalidUserIDException
    {
        if (userDao.getUserByID(UserID) == null) throw new InvalidUserIDException();
        return userDao.getUserByID(UserID);
    }
    @Override
    public void deleteUser(String userID) throws UserIDNotFoundUserDaoMockException {
        userDao.deleteUser(userID);
    }
    
    @Override
    public User editUser(User changingUser) throws UserDoesNotExistException, EmptyUserException {
        if (isEmpty(changingUser)) {
            throw new EmptyUserException();
        }
        User editedUser = userDao.changeUser(changingUser);
        if (editedUser == null) {
            throw new UserDoesNotExistException();
        }
        return editedUser;
    }

    private boolean isEmpty(User changingUser) {
        if (changingUser == null
                || changingUser.getAge() == User.DEFAULT_USER_AGE
                || changingUser.getName().equals(User.DEFAULT_USER_NAME)) {
            return true;
        }
        return false;
    }
    @Override
    public User postNewUser(User user) throws UserAlreadyExistsException {
        userDao.postNewUser(user);
        return user;
    }
}

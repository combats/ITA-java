package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ArrayList<String> getAllUsersID() {
        return userDao.getAllUsersID();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getUserByID(String UserID) throws InvalidUserIDException {
        if (userDao.getUserByID(UserID) == null) throw new InvalidUserIDException();
        return userDao.getUserByID(UserID);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String deleteUser(String userID) throws UserDoesNotExistException {
       return userDao.deleteUser(userID);
    }
    
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
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

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User postNewUser(User user) throws UserAlreadyExistsException {
        userDao.postNewUser(user);
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    private boolean isEmpty(User changingUser) {
        if (changingUser == null
                || changingUser.getAge() == User.DEFAULT_USER_AGE
                || changingUser.getName().equals(User.DEFAULT_USER_NAME)) {
            return true;
        }
        return false;
    }

}

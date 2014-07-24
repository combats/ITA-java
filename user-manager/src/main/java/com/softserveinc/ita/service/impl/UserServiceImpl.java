package com.softserveinc.ita.service.impl;

import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<String> getAllUsersID() {
        return userDao.getAllUsersId();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User getUserByID(String UserID) throws InvalidUserIDException {
        if (userDao.getUserById(UserID) == null) throw new InvalidUserIDException();
        return userDao.getUserById(UserID);
    }
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String deleteUser(String userID){
       return userDao.deleteUserById(userID);
    }
    
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User editUser(User changingUser) throws UserDoesNotExistException, EmptyUserException {
        if (isEmpty(changingUser)) {
            throw new EmptyUserException();
        }
        User editedUser = userDao.updateUser(changingUser);
        if (editedUser == null) {
            throw new UserDoesNotExistException();
        }
        return editedUser;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User postNewUser(User user){
        userDao.addUser(user);
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    private boolean isEmpty(User changingUser) {
        if (changingUser == null
//                || changingUser.get() == User.DEFAULT_USER_AGE
                || changingUser.getName().isEmpty()) {
            return true;
        }
        return false;
    }

}

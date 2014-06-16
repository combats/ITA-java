package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.userDAO.UserDAOMockImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOMockImpl userDAOMockImpl;

    @Override
    public User editUser(User changingUser) throws UserDoesNotExistException, EmptyUserException {
        if (isEmpty(changingUser)) {
            throw new EmptyUserException();
        }
        User editedUser = userDAOMockImpl.changeUser(changingUser);
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
}

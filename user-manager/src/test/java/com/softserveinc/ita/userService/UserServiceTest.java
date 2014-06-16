package com.softserveinc.ita.userService;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends UserServiceBaseTest{

    @Autowired
    private UserService userServiceImpl;

    @Test(expected = UserDoesNotExistException.class)
    public void testEditUserWithNotExistingIdAndExpectException() throws UserDoesNotExistException, EmptyUserException {
        User user = new User("id4", "name", 2);
        userServiceImpl.editUser(user);
    }

    @Test(expected = EmptyUserException.class)
    public void testEditUserWithEmptyInformation() throws UserDoesNotExistException, EmptyUserException {
        User user = new User("id2");
        userServiceImpl.editUser(user);
    }

    @Test
    public void testEditUserWithExistingUser() throws UserDoesNotExistException, EmptyUserException {
        User user = new User("id2", "name", 2);
        userServiceImpl.editUser(user);
    }
}

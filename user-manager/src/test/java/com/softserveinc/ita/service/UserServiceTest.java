package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class UserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddNewUserWithDuplicateIDThrowsException() throws Exception {
        User testUser = new User("1");
        userService.postNewUser(testUser);
    }

    @Test
    public void testAddNewUserWithOkIDReturnsThatUser() throws Exception {
        User testUser = new User("42");
        assertEquals(testUser, userService.postNewUser(testUser));
    }
} 

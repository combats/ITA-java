package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.InvalidUserIDException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByExistingIdAndExpectEquals() throws Exception {
        String userId = "1";
        User expectedApplicant = new User("1");
        assertEquals(expectedApplicant,userService.getUserByID(userId));
    }

    @Test(expected = InvalidUserIDException.class)
    public void testGetApplicantByNotExistingIdAndExpectException() throws Exception {
        String userID = "ABC";
        userService.getUserByID(userID);
    }
} 

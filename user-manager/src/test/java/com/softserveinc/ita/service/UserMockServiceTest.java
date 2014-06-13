package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.service.impl.UserMockService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UserMockServiceTest {
    UserMockService mockService;

    @Before
    public void setUp() throws Exception {
        mockService = new UserMockService();
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddNewUserWithDuplicateIDThrowsException() throws Exception {
        User testUser = new User("1");
        mockService.postNewUser(testUser);
    }

    @Test
    public void testAddNewUserWithOkIDReturnsThatUser() throws Exception {
        User testUser = new User("42");
        assertEquals(testUser, mockService.postNewUser(testUser));
    }

} 
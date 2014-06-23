package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMockDAOTest extends BaseDAOTest {

    @Autowired
    private UserDAO userDAO;
    @Test(expected = UserAlreadyExistsException.class)
    public void testAddNewUserWithDuplicateIDThrowsException() throws Exception {
        User testUser = new User("1");
        userDAO.postNewUser(testUser);
    }

    @Test
    public void testAddNewUserWithOkIDReturnsThatUser() throws Exception {
        User testUser = new User("42");
        Assert.assertEquals(testUser, userDAO.postNewUser(testUser));
    }

}

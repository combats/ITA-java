package com.softserveinc.ita.dao;

import com.softserveinc.ita.dao.impl.UserDAOMockImpl;
import com.softserveinc.ita.entity.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertEquals;

public class UserMockDAOTest extends BaseDAOTest {
    private UserDAO mockDAO;
    private String validID;
    private String invalidID;

    @Before
    public void setUp() throws Exception {
        mockDAO = new UserDAOMockImpl();
        validID = "1";
        invalidID = "ABS";
    }

    @Test
    public void testGetUserByIDReturnsCorrectUser() throws Exception {
        User testUser = new User("1");
        assertEquals(testUser, mockDAO.getUserByID("1"));
    }

    @Test
    public void testGetUserByIDReturnsNullForInvalidUserID() throws Exception {
        assertNull(mockDAO.getUserByID(invalidID));
    }

    @Test
    public void testGetUserByIDReturnsNotNullForValidUserID() throws Exception {
        assertNotNull(mockDAO.getUserByID(validID));
    }

}

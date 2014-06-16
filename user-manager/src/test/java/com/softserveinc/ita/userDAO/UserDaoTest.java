package com.softserveinc.ita.userDAO;

import com.softserveinc.ita.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static junit.framework.Assert.*;

public class UserDAOTest extends UserDAOBaseTest {

    @Autowired
    @Qualifier("userDAOMock")
    private UserDAO userDAO;

    @Test
    public void testExistingUserEditingAndExpectTrue(){
        User user = new User("id1","name",43);
        assertEquals(userDAO.changeUser(user), user);
    }

    @Test
    public void testNotExistingUserAndExpectFalse(){
        User user = new User("id4","name",43);
        assertNull(userDAO.changeUser(user));
    }

    @Test
    public void testNullUserParameterAndExpectFalse(){
        User user = null;
        assertNull(userDAO.changeUser(user));
    }
}

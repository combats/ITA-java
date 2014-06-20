package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.*;

public class UserDAOTest extends UserDAOBaseTest {

    @Autowired
    private UserDAO userDAOMockImpl;

    @Test
    public void testExistingUserEditingAndExpectTrue(){
        User user = new User("id1","name",43);
        assertEquals(userDAOMockImpl.changeUser(user), user);
    }

    @Test
    public void testNotExistingUserAndExpectFalse(){
        User user = new User("id4","name",43);
        assertNull(userDAOMockImpl.changeUser(user));
    }

    @Test
    public void testNullUserParameterAndExpectFalse(){
        User user = null;
        assertNull(userDAOMockImpl.changeUser(user));
    }
}

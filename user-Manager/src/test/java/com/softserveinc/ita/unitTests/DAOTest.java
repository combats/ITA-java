package com.softserveinc.ita.unitTests;

import com.softserveinc.ita.BaseTest;
import com.softserveinc.ita.DAO.UserDAO;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.service.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAOTest extends BaseTest {
    @Autowired
    @Qualifier("userDAOImpl")
    private UserDAO userDAO;

    @Test
    public void testGetUsersAndExpectDefinedList() {
        List<User> sampleUserList = new ArrayList<>();
        Collections.addAll(sampleUserList, new User("id3"), new User("idY"), new User("id09z"));
        Assert.assertEquals(sampleUserList, userDAO.getUsers());
    }
}

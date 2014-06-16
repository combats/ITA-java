package com.softserveinc.ita.service;

import com.softserveinc.ita.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class UserMockServiceTest extends BaseServiceTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUsersIDAndExpectListOfUsersID() throws Exception {
        ArrayList<String> usersIDList = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        assertEquals(userDao.getAllUsersID(), usersIDList);
    }
} 
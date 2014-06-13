package com.softserveinc.ita.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserDaoTests extends BaseDAOTest{
    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUsersIDReturnsListOfUsersID() throws Exception{
        ArrayList<String> testUsersIDList = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
       assertEquals(userDao.getAllUsersID(), testUsersIDList);
    }

}

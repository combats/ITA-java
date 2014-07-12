package com.softserveinc.ita.dao;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.softserveinc.ita.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-test-context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class UserDAOTest  {

    @Autowired
    UserDAO userDAO;

    @Test
    @DatabaseSetup("sampleData.xml")
    public void testGetAllUsers() {
        List<User> allUsers = this.userDAO.getAllUsers();
        assertEquals(3, allUsers.size());
    }

    @Test
    @DatabaseSetup("sampleData.xml")
    public void testGetUserById() {
        this.userDAO.getUserById("1");
    }

}

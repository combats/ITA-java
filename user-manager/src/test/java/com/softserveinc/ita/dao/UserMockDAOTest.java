package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserMockDAOTest extends BaseDAOTest {
    @Autowired
    private UserDAO userDAOImpl;

    @Test
    public void testGetUsersAndExpectDefinedList() {
        List<User> sampleUserList = new ArrayList<>();
        Collections.addAll(sampleUserList, new User("id3"), new User("idY"), new User("id09z"));
        Assert.assertEquals(sampleUserList, userDAOImpl.getUsers());
    }
}

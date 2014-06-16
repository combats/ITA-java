package com.softserveinc.ita.unitTests;

import com.softserveinc.ita.BaseTest;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.service.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceTest extends BaseTest {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Test
    public void testGetUsersAndExpectDefinedList() {
        List<User> sampleUserList = new ArrayList<>();
        Collections.addAll(sampleUserList, new User("id3"), new User("idY"), new User("id09z"));
        Assert.assertEquals(sampleUserList, userService.getUsers());
    }

}

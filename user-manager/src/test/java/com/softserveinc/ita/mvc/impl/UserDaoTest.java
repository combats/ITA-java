package com.softserveinc.ita.mvc.impl;

import com.softserveinc.ita.dao.UserDao;
import com.softserveinc.ita.dao.impl.UserMockDao;
import com.softserveinc.ita.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.softserveinc.ita.mvc.BaseMVCTest;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest extends BaseMVCTest {

    @Mock
    private UserMockDao userDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteUser() throws Exception {
        String muserID = "123";
        userDao.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
    }
}

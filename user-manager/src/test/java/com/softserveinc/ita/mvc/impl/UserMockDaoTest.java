package com.softserveinc.ita.mvc.impl;

import com.softserveinc.ita.dao.impl.UserMockDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMockDaoTest {
    @Mock
    private UserMockDao userDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteUserAndDeletUserInvokingExpected() throws Exception {
        String muserID = "123";
        userDao.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
        verify(userDao, times(1)).deleteUser(muserID);
    }
}

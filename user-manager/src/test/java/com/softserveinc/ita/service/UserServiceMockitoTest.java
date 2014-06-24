package com.softserveinc.ita.service;


import com.softserveinc.ita.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceMockitoTest extends BaseServiceTest {
    @Autowired
    @InjectMocks
    private UserService userService;

    @Autowired
    @Mock
    private UserDAO userDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testDeleteUser() throws Exception {
        String muserID = "123";

        when(userDao.deleteUser(muserID)).thenReturn(muserID);
        String result = userService.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
        assertEquals(result, muserID);
    }

    @Test
    public void testDeleteUserAndExpectedIsException() {
        String muserID = "124";

        when(userDao.deleteUser(muserID)).thenReturn(null);
        String result = userService.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
        assertEquals(result, null);
    }
}

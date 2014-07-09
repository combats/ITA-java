package com.softserveinc.ita.service;


import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.dao.impl.UserDAOMockImpl;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
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
        assertEquals(muserID, result);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testDeleteUserAndExpectedIsException() throws UserDoesNotExistException {
        String muserID = "124";

        doThrow(new UserDoesNotExistException()).when(userDao).deleteUser(muserID);
        userService.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
    }
}

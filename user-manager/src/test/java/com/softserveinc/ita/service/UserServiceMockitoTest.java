package com.softserveinc.ita.service;


import com.softserveinc.ita.dao.UserDAO;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

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

        userService.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
    }

    @Test(expected = UserIDNotFoundUserDaoMockException.class)
    public void testDeleteUserAndExpectedIsException() throws UserIDNotFoundUserDaoMockException {
        String muserID = "124";

        doThrow(new UserIDNotFoundUserDaoMockException()).when(userDao).deleteUser(muserID);
        userService.deleteUser(muserID);
        verify(userDao).deleteUser(muserID);
    }
}

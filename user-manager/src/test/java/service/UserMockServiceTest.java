package service;

import com.softserveinc.ita.entity.User;
import exception.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class UserMockServiceTest {
    UserMockService mockService;

    @Before
    public void setUp() throws Exception {
        mockService = new UserMockService();
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddNewUserWithDuplicateIDThrowsException() throws Exception {
        User testUser = new User("1");
        mockService.postNewUser(testUser);
    }

    @Test
    public void testAddNewUserWithOkIDReturnsThatUser() throws Exception {
        User testUser = new User("42");
        assertEquals(testUser, mockService.postNewUser(testUser));
    }

} 
package service;

import com.softserveinc.ita.entity.User;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class UserMockServiceTest {
    UserMockService mockService;
    String validID;
    String invalidID;
    @Before
    public void setUp() throws Exception {
    mockService = new UserMockService();
    validID = "1";
    invalidID = "ABS";
    }

    @Test
public void testGetUserByIDReturnsCorrectUser() throws Exception {
     User testUser = new User("1");
     assertEquals(testUser, mockService.getUserByID("1"));
}
    @Test
      public void testGetUserByIDReturnsNullforInvalidUserID() throws Exception {
        assertNull(mockService.getUserByID(invalidID));
    }
    @Test
    public void testGetUserByIDReturnsNotNullforValidUserID() throws Exception {
        assertNotNull(mockService.getUserByID(validID));
    }


} 

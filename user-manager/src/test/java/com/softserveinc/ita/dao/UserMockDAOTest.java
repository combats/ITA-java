package com.softserveinc.ita.dao;

import com.softserveinc.ita.dao.impl.UserDAOMockImpl;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Field;
import java.util.*;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertEquals;

public class UserMockDAOTest extends BaseDAOTest {
    @Autowired
    private UserDAO userDAO;
    
    private String validID;
    private String invalidID;

    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAOMockImpl();
        validID = "1";
        invalidID = "ABS";
    }

    @Test
    public void testGetUserByIDReturnsCorrectUser() throws Exception {
        User testUser = new User("1");
        assertEquals(testUser, userDAO.getUserByID("1"));
    }

    @Test
    public void testGetUserByIDReturnsNullForInvalidUserID() throws Exception {
        assertNull(userDAO.getUserByID(invalidID));
    }

    @Test
    public void testGetUserByIDReturnsNotNullForValidUserID() throws Exception {
        assertNotNull(userDAO.getUserByID(validID));
    }

    @Test
    public void testGetAllUsersIDReturnsListOfUsersID() throws Exception {
        ArrayList<String> testUsersIDList = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        Assert.assertEquals(userDAO.getAllUsersID(), testUsersIDList);
    }

    /**
     * Such strange construction with using reflection
     * are needed because I am testing void method with
     * JUnit rather than Mockito (all attempts to use Mockito
     * failed)
     */
    @Test
    public void testDeleteUser() throws Exception {
        String muserID = "123";

        Set<String> keySet = new HashSet<>();
        Set<String> expected = new HashSet<>();
        expected.add("121");
        expected.add("122");

        userDAO.deleteUser(muserID);

        userDAO.getClass().getDeclaredField("dbOfUsers");

        Field fld = null;

        try {

            fld = userDAO.getClass().getDeclaredField("dbOfUsers");
            fld.setAccessible(true);
            Map<String, User> mockDB = (Map<String, User>) fld.get(userDAO);
            keySet = mockDB.keySet();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expected, keySet);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testDeleteUserAndExpectedIsException() throws UserDoesNotExistException {
        String muserID = "124";

        userDAO.deleteUser(muserID);
    }

    @Test
    public void testExistingUserEditingAndExpectTrue(){
        User user = new User("id1","name",43);
        assertEquals(userDAO.changeUser(user), user);
    }

    @Test
    public void testNotExistingUserAndExpectFalse(){
        User user = new User("id4","name",43);
        assertNull(userDAO.changeUser(user));
    }

    @Test
    public void testNullUserParameterAndExpectFalse(){
        User user = null;
        assertNull(userDAO.changeUser(user));
    }
    @Test(expected = UserAlreadyExistsException.class)
    public void testAddNewUserWithDuplicateIDThrowsException() throws Exception {
        User testUser = new User("1");
        userDAO.postNewUser(testUser);
    }

    @Test
    public void testAddNewUserWithOkIDReturnsThatUser() throws Exception {
        User testUser = new User("42");
        Assert.assertEquals(testUser, userDAO.postNewUser(testUser));
    }

    @Test
    public void testGetUsersAndExpectDefinedList() {
        List<User> sampleUserList = new ArrayList<>();
        Collections.addAll(sampleUserList, new User("id3"), new User("idY"), new User("id09z"));
        Assert.assertEquals(sampleUserList, userDAO.getUsers());
    }

}
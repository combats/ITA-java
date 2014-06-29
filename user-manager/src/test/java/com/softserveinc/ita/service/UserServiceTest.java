package com.softserveinc.ita.service;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.EmptyUserException;
import com.softserveinc.ita.exception.InvalidUserIDException;
import com.softserveinc.ita.exception.UserDoesNotExistException;
import com.softserveinc.ita.exception.UserAlreadyExistsException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest extends BaseServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByExistingIdAndExpectEquals() throws Exception {
        String userId = "1";
        User expectedApplicant = new User("1");
        assertEquals(expectedApplicant,userService.getUserByID(userId));
    }

    @Test(expected = InvalidUserIDException.class)
    public void testGetUserByNotExistingIdAndExpectException() throws Exception {
        String userID = "ABC";
        userService.getUserByID(userID);
    }
    @Test
    public void testGetAllUsersIDAndExpectListOfUsersID() throws Exception {
        ArrayList<String> usersIDList = new ArrayList<String>() {{
            add("1");
            add("2");
            add("3");
        }};
        assertEquals(userService.getAllUsersID(), usersIDList);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void testEditUserWithNotExistingIdAndExpectException() throws UserDoesNotExistException, EmptyUserException {
        User user = new User("id4", "name", 2);
        userService.editUser(user);
    }

    @Test(expected = EmptyUserException.class)
    public void testEditUserWithEmptyInformation() throws UserDoesNotExistException, EmptyUserException {
        User user = new User("id2");
        userService.editUser(user);
    }

    @Test
    public void testEditUserWithExistingUser() throws UserDoesNotExistException, EmptyUserException {
        User user = new User("id2", "name", 2);
        userService.editUser(user);
    }
    @Test(expected = UserAlreadyExistsException.class)
    public void testAddNewUserWithDuplicateIDThrowsException() throws Exception {
        User testUser = new User("1");
        userService.postNewUser(testUser);
    }

    @Test
    public void testAddNewUserWithOkIDReturnsThatUser() throws Exception {
        User testUser = new User("42");
        assertEquals(testUser, userService.postNewUser(testUser));
    }

    @Test
    public void testGetUsersAndExpectDefinedList() {
        List<User> sampleUserList = new ArrayList<>();
        Collections.addAll(sampleUserList, new User("id3"), new User("idY"), new User("id09z"));
        assertEquals(sampleUserList, userService.getUsers());
    }

} 
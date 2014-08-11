package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.LogInPage;
import com.softserveinc.com.pageobjects.UiUsersPage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersTestIT extends BaseUIIntegrationTest {
    private static final String USER_NAME = "Vasiliy";
    private static final String USER_SURNAME = "Pupkin";
    private static final String USER_PASSWORD = "qwerty";
    private static final String USER_EMAIL = "qwerty@gmail.com";
    private static final String USER_PHONE = "1234567890";
    private static final String USER_ROLE = "USER"; // ADMIN, USER, HR

    private static final String EDITED_USER_NAME = "Vasya";
    private static final String EDITED_USER_SURNAME = "Pupkin";
    private static final String EDITED_USER_PASSWORD = "qwerty";
    private static final String EDITED_USER_EMAIL = "qwerty@gmail.inc";
    private static final String EDITED_USER_PHONE = "0987654321";
    private static final String EDITED_USER_ROLE = "ADMIN"; // ADMIN, USER, HR



//    @Test
    public void testAddUser() {
        getDrv().get(getSiteBase().toString());

        UiUsersPage usersPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers()
                .addUser(USER_NAME, USER_SURNAME, USER_PASSWORD, USER_EMAIL, USER_PHONE, USER_ROLE);

        assertTrue(getDrv().getPageSource().contains(USER_NAME + " " + USER_SURNAME)); // i need to add click on button with us_role before assertion
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase())); // don't works because of # in the end of path.

        usersPage.logOut();
    }

//    @Test
    public void testEditUser() {
        getDrv().get(getSiteBase().toString());

        UiUsersPage usersPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers()
                .editUser(USER_ROLE, USER_EMAIL,EDITED_USER_NAME, EDITED_USER_SURNAME, EDITED_USER_PASSWORD,
                        EDITED_USER_EMAIL, EDITED_USER_PHONE, EDITED_USER_ROLE);

        assertTrue(getDrv().getPageSource().contains(EDITED_USER_NAME + " " + EDITED_USER_SURNAME));
        assertFalse(getDrv().getPageSource().contains(USER_NAME + " " + USER_SURNAME));
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));

        usersPage.logOut();
    }

//    @Test
    public void testDeleteUser() {
        getDrv().get(getSiteBase().toString());

        UiUsersPage usersPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers()
                .deleteUser(EDITED_USER_ROLE, EDITED_USER_EMAIL);

        assertFalse(getDrv().getPageSource().contains(EDITED_USER_NAME + " " + EDITED_USER_SURNAME));
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));

        usersPage.logOut();
    }

    @Test
    public void runTests() {
        testAddUser();
        testEditUser();
        testDeleteUser();
    }
}

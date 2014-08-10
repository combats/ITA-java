package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.LogInPage;
import com.softserveinc.com.pageobjects.UiUsersPage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersTestIT extends BaseUIIntegrationTest {
    @Test
    public void testAddUser() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers().addUser("Vasiliy", "Pupkin", "qwerty",
                "qwerty@gmail.com", "1234567890");

        assertTrue(getDrv().getPageSource().contains("Vasiliy Pupkin"));
//        assertTrue(UiQuestionsPage.isAtQuestionsPage(getDrv(), getSiteBase())); // don't works because of # in the end of path.
    }

    @Test
    public void testEditUser() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers().editUser("Vasili", "1234567890");

        assertTrue(getDrv().getPageSource().contains("Vasili Pupkin"));
//        assertTrue(UiQuestionsPage.isAtQuestionsPage(getDrv(), getSiteBase()));
    }

    @Test
    public void testDeleteUser() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers().deleteUser();

        assertFalse(getDrv().getPageSource().contains("Vasili Pupkin"));
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));
    }
}

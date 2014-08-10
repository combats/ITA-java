package com.softserveinc.com.tests;


import com.softserveinc.com.pageobjects.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ManePageIT extends BaseUIIntegrationTest {


    @Test
    public void testWeSeeHelloWorld() {

        getDrv().get(getSiteBase().toString());
        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD);
        assertTrue(ManePage.isAtManePage(getDrv(), getSiteBase()));
        assertTrue(getDrv().getPageSource().contains("IT Interviewer"));
        assertTrue(getDrv().getPageSource().contains("Groups"));
    }

    @Test
    public void testGetGroupsPage() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups();

        assertTrue(UiGroupsPage.isAtGroupsPage(getDrv(), getSiteBase()));
    }

    @Test
    public void testGetQuestionsPage() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToQuestions();

        assertTrue(UiQuestionsPage.isAtQuestionsPage(getDrv(), getSiteBase()));
    }

    @Test
    public void testGetUsersPage() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers();

        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));
    }
}

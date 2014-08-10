package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.LogInPage;
import com.softserveinc.com.pageobjects.ManePage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LogInPageIT extends BaseUIIntegrationTest {
    @Test
    public void testWeSeeHelloWorld() {
        getDrv().get(getSiteBase().toString());
        new LogInPage(getDrv(), getSiteBase()).logIn("dod@dod.com", "dod");
        assertTrue(ManePage.isAtManePage(getDrv(), getSiteBase()));
        assertTrue(getDrv().getPageSource().contains("IT Interviewer"));
        assertTrue(getDrv().getPageSource().contains("Groups"));
    }
}

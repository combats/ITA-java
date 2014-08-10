package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.LogInPage;
import com.softserveinc.com.pageobjects.UiGroupsPage;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GroupsPageIT extends BaseUIIntegrationTest {
    private static final String GROUP_NAME = "KV-666";
    private static final String ADDRESS = "unknown address";
    private static final String COURSE = "Java"; // available now values: "Java", ".NET", "DevOps", "JavaScript".
    private static final String CAPACITY = "12";
    private static final String START_BOARDING_DATE = "08/05/2014"; // must be like mm/dd/yyyy
    private static final String START_DATE = "08/06/2014"; // must be bigger than boarding date
    private static final String START_TIME = "11:00";
    private static final String END_DATE = "08/14/2014";

    private static final String EDITED_GROUP_NAME = "!-666";
    private static final String EDITED_ADDRESS = "unknown address qqq";
    private static final String EDITED_COURSE = ".NET"; // available now values: "Java", ".NET", "DevOps", "JavaScript".
    private static final String EDITED_CAPACITY = "12";
    private static final String EDITED_START_BOARDING_DATE = "09/11/2014";
    private static final String EDITED_START_DATE = "11/18/2015"; // must be bigger than boarding date
    private static final String EDITED_START_TIME = "15:00";
    private static final String EDITED_END_DATE = "12/14/2015";

    @Test
    public void testAddGroup() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups()
                .addGroup(GROUP_NAME,
                          ADDRESS,
                          COURSE,
                          CAPACITY,
                          START_BOARDING_DATE,
                          START_DATE,
                          START_TIME,
                          END_DATE);

        assertTrue(getDrv().getPageSource().contains(GROUP_NAME));
        assertTrue(UiGroupsPage.isAtGroupsPage(getDrv(), getSiteBase())); //because of # in the end
    }

    @Test
    public void testEditGroup() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups()
                .editGroup(GROUP_NAME,
                           EDITED_GROUP_NAME,
                           EDITED_ADDRESS,
                           EDITED_COURSE,
                           EDITED_CAPACITY,
                           EDITED_START_BOARDING_DATE,
                           EDITED_START_DATE,
                           EDITED_START_TIME,
                           EDITED_END_DATE);

        assertTrue(getDrv().getPageSource().contains(EDITED_GROUP_NAME));
        assertTrue(UiGroupsPage.isAtGroupsPage(getDrv(), getSiteBase())); //because of # in the end
    }

    @Test
    public void testDeleteGroup() {
        getDrv().get(getSiteBase().toString());

        new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToGroups().deleteGroup(EDITED_GROUP_NAME);

        assertFalse(getDrv().getPageSource().contains(EDITED_GROUP_NAME));
        assertTrue(UiGroupsPage.isAtGroupsPage(getDrv(), getSiteBase())); //because of # in the end
    }
}

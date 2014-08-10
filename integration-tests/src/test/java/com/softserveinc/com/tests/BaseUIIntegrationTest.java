package com.softserveinc.com.tests;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URI;

@WebAppConfiguration
@ContextConfiguration({"classpath:it-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseUIIntegrationTest {
    protected static final String LOGIN = "petr@crupet.com";
    protected static final String PASSWORD = "dod";

    @Autowired
    private URI siteBase;

    @Autowired
    private WebDriver drv;

    /**
     * Needed for clean-up after each test
     * (deleting cookies and closing opened popups)
     */
    @Before
    public void setUp() {
        getDrv().manage().deleteAllCookies();
        getDrv().get(siteBase.toString());
    }

    public URI getSiteBase() {
        return siteBase;
    }

    public WebDriver getDrv() {
        return drv;
    }

//    @After
//    public void tearDown() throws Exception {
//        drv.quit();
//    }
}

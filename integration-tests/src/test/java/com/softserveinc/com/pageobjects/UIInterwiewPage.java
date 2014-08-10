package com.softserveinc.com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.URI;

public class UIInterwiewPage {

    private final WebDriver drv;
    private final URI siteBase;

    public static boolean isAtGroupsPage(WebDriver drv, URI siteBase) {
        boolean res;
        res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/interview");
        if(!res) {
            res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/interview#");
        }
        return res;
    }

    public UIInterwiewPage(WebDriver drv, URI siteBase) {
//        super(drv, siteBase);
        if (!isAtGroupsPage(drv, siteBase)) {
            throw new IllegalStateException("This is not Interview page, current page is: " + drv.getCurrentUrl());
        }
        PageFactory.initElements(drv, this); //populates all fields @FindBy...
        this.drv = drv;
        this.siteBase = siteBase;
    }
}

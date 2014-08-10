package com.softserveinc.com.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.URI;

public class ManePage extends AbstractPageObject{

    public static boolean isAtManePage(WebDriver drv, URI siteBase) {
//        return drv.getCurrentUrl().equals(siteBase);            // was like this but was assertion (equals) troubles
                                                                  // because of siteBase.toString() was missed
                                                                  // actually was equal but not for equals()...
//        return drv.getCurrentUrl().equals(siteBase.toString()); // this is right way...
        return drv.getCurrentUrl().matches(siteBase.toString());
    }

    public ManePage(WebDriver drv, URI siteBase) {
        super(drv, siteBase);
        if (!isAtManePage(drv, siteBase)) {
            throw new IllegalStateException("This is not main page, current page is: " + drv.getCurrentUrl());
        }
        PageFactory.initElements(drv, this); //populates all fields @FindBy...
    }
}

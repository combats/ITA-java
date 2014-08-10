package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URI;

public class LogInPage {
    private final WebDriver drv;
    private final URI siteBase;

    public static boolean isAtUsersPage(WebDriver drv, URI siteBase) {
        return drv.getCurrentUrl().matches(siteBase.toString() + "login");
    }

    public LogInPage(WebDriver drv, URI siteBase) {
        if (!isAtUsersPage(drv, siteBase)) {
            throw new IllegalStateException("This is not login page, current page is: " + drv.getCurrentUrl());
        }
        this.drv = drv;
        this.siteBase = siteBase;
    }

    public ManePage logIn(String login, String password) {

        drv.findElement(By.name("username")).clear();
        drv.findElement(By.name("username")).sendKeys(login);
        drv.findElement(By.name("password")).clear();
        drv.findElement(By.name("password")).sendKeys(password);
        drv.findElement(By.name("login")).click();

        return new ManePage(drv, siteBase);
    }
}

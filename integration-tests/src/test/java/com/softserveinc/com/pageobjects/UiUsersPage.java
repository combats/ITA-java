package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.net.URI;

public class UiUsersPage extends AbstractPageObject{

    public static boolean isAtUsersPage(WebDriver drv, URI siteBase) {
        boolean res;
        res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/users");
        if(!res) {
            res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/users#");
        }
        return res;
    }

    public UiUsersPage(WebDriver drv, URI siteBase) {
        super(drv, siteBase);
        if (!isAtUsersPage(drv, siteBase)) {
            throw new IllegalStateException("This is not users page, current page is: " + drv.getCurrentUrl());
        }
        PageFactory.initElements(drv, this); //populates all fields @FindBy...
    }

    public UiUsersPage addUser(String name, String secName, String password, String email, String phone) {

        drv.findElement(By.id("addUserButton")).click();
        drv.findElement(By.id("uName")).clear();
        drv.findElement(By.id("uName")).sendKeys(name);
        drv.findElement(By.id("uSurname")).clear();
        drv.findElement(By.id("uSurname")).sendKeys(secName);
        drv.findElement(By.id("uPassword")).clear();
        drv.findElement(By.id("uPassword")).sendKeys(password);
        drv.findElement(By.id("showHidePassword")).click();
        drv.findElement(By.id("uEmail")).clear();
        drv.findElement(By.id("uEmail")).sendKeys(email);
        drv.findElement(By.id("uPhone")).clear();
        drv.findElement(By.id("uPhone")).sendKeys(phone);
        new Select(drv.findElement(By.id("uRole"))).selectByVisibleText("ADMIN");
        drv.findElement(By.id("saveUButton")).click();

        return this;
    }

    public UiUsersPage editUser(String name, String phone) {
        drv.findElement(By.xpath("//div[5]/div/a/span/i")).click();
        drv.findElement(By.id("uName")).clear();
        drv.findElement(By.id("uName")).sendKeys(name);
        drv.findElement(By.id("uPhone")).clear();
        drv.findElement(By.id("uPhone")).sendKeys(phone);
        new Select(drv.findElement(By.id("uRole"))).selectByVisibleText("USER");
        drv.findElement(By.id("saveUButton")).click();

        return this;
    }

    public UiUsersPage deleteUser() {
        drv.findElement(By.linkText("USER")).click();
        drv.findElement(By.xpath("//div[2]/div/div/div/div/div/div[3]/div/a[2]/span/i")).click();
        drv.findElement(By.id("okDUButton")).click();

        return this;
    }
}

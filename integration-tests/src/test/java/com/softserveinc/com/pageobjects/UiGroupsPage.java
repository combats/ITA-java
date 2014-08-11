package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class UiGroupsPage extends AbstractPageObject{
    private String ID;

    public static boolean isAtGroupsPage(WebDriver drv, URI siteBase) {
        boolean res;
        res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/groups");
        if(!res) {
            res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/groups#");
        }
        return res;
    }

    public UiGroupsPage(WebDriver drv, URI siteBase) {
        super(drv, siteBase);
        if (!isAtGroupsPage(drv, siteBase)) {
            throw new IllegalStateException("This is not groups page, current page is: " + drv.getCurrentUrl());
        }
        PageFactory.initElements(drv, this);
    }

    public UIApplicantsPage goToApplicants(String groupName) {
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("Planned");
        drv.findElement(By.cssSelector("option[value=\"PLANNED\"]")).click();
        setGroupID(groupName);
        drv.findElement(By.linkText(groupName)).click();
        return new UIApplicantsPage(drv, siteBase, ID);
    }

    public UiGroupsPage addGroup(String groupName, String address, String course, String capacity, String strBoarding,
                                 String strDate, String strtTime, String endDate) {
        drv.findElement(By.id("AddGroup")).click();
        drv.findElement(By.id("gName")).clear();
        drv.findElement(By.id("gName")).sendKeys(groupName);
        drv.findElement(By.id("gAddress")).clear();
        drv.findElement(By.id("gAddress")).sendKeys(address);
        new Select(drv.findElement(By.id("gCourse"))).selectByVisibleText(course);
        drv.findElement(By.id("gCapacity")).clear();
        drv.findElement(By.id("gCapacity")).sendKeys(capacity);
        drv.findElement(By.id("gStartBoardingDate")).click();
        drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pickDate(strBoarding);
        drv.findElement(By.id("gStartDate")).click();
        drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pickDate(strDate);
        drv.findElement(By.id("gStartTime")).clear();
        drv.findElement(By.id("gStartTime")).sendKeys(strtTime);
        drv.findElement(By.id("gEndDate")).click();
        drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pickDate(endDate);
        drv.findElement(By.id("saveUButton")).click();
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("All groups");
        drv.findElement(By.cssSelector("option[value=\"All groups\"]")).click();
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        setGroupID(groupName);

        return this;
    }

    public UiGroupsPage editGroup(String groupName,String newGroupName, String editedAddress, String editedCourse,
                                  String editedCapacity, String editedBoardDate, String editedStartDate,
                                  String editedStartTime, String editedEndDate) {

        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        setGroupID(groupName);
        drv.findElement(By.xpath("//img[@onclick='viewEditInformationDialog(\"" + ID + "\")']")).click();
        if(newGroupName != null) {
            drv.findElement(By.id("gName")).clear();
            drv.findElement(By.id("gName")).sendKeys(newGroupName);
        }
        if(editedAddress != null) {
            drv.findElement(By.id("gAddress")).clear();
            drv.findElement(By.id("gAddress")).sendKeys(editedAddress);
        }
        if(editedCourse != null) {
            new Select(drv.findElement(By.id("gCourse"))).selectByVisibleText(editedCourse);
        }
        if(editedCapacity != null) {
            drv.findElement(By.id("gCapacity")).clear();
            drv.findElement(By.id("gCapacity")).sendKeys(editedCapacity);
        }
        if(editedBoardDate != null) {
            drv.findElement(By.id("gStartBoardingDate")).click();
            drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            pickDate(editedBoardDate);
        }
        if(editedStartDate != null) {
            drv.findElement(By.id("gStartDate")).click();
            drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            pickDate(editedStartDate);
        }
        if(editedStartTime != null) {
            drv.findElement(By.id("gStartTime")).clear();
            drv.findElement(By.id("gStartTime")).sendKeys(editedStartTime);
        }
        if(editedEndDate != null) {
            drv.findElement(By.id("gEndDate")).click();
            drv.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            pickDate(editedEndDate);
        }
        drv.findElement(By.id("saveUButton")).click();
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("All groups");
        drv.findElement(By.cssSelector("option[value=\"All groups\"]")).click();

        return this;
    }

    public UiGroupsPage deleteGroup(String groupName) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        setGroupID(groupName);
        drv.findElement(By.xpath("//img[@onclick='viewDeleteDialog(\"" + ID + "\");']")).click();
        drv.findElement(By.id("okDUButton")).click();
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("All groups");
        drv.findElement(By.cssSelector("option[value=\"All groups\"]")).click();

        return this;
    }

    public void setGroupID(String groupName) {
        String hrefID = drv.findElement(By.linkText(groupName)).getAttribute("href");
        String delStr = siteBase.toString() + "ui/group?groupID=";
        ID = hrefID.replace(delStr, "");
    }
}

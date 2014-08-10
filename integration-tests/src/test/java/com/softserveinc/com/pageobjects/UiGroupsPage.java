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
        PageFactory.initElements(drv, this); //populates all fields @FindBy...
    }

    public UIApplicantsPage goToApplicants(String groupName) {
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("Planned");
        drv.findElement(By.cssSelector("option[value=\"PLANNED\"]")).click();
        setGroupID(groupName);
        drv.findElement(By.linkText(groupName)).click();
        return new UIApplicantsPage(drv, siteBase, ID);
    }

    public UiGroupsPage addGroup(String groupName, String address, String course, String capacity, String strBoarding, String strDate, String strtTime, String endDate) {
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("Planned");
        drv.findElement(By.cssSelector("option[value=\"PLANNED\"]")).click();
        drv.findElement(By.id("AddGroup")).click();
        drv.findElement(By.id("gName")).clear();
        drv.findElement(By.id("gName")).sendKeys(groupName);
        drv.findElement(By.id("gAddress")).clear();
        drv.findElement(By.id("gAddress")).sendKeys(address);
        new Select(drv.findElement(By.id("gCourse"))).selectByVisibleText(course);
        drv.findElement(By.id("gCapacity")).clear();
        drv.findElement(By.id("gCapacity")).sendKeys(capacity);
        drv.findElement(By.id("gStartBoardingDate")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        pickDate(strBoarding);
        drv.findElement(By.id("gStartDate")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        pickDate(strDate);
        drv.findElement(By.id("gStartTime")).clear();
        drv.findElement(By.id("gStartTime")).sendKeys(strtTime);
        drv.findElement(By.id("gEndDate")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        pickDate(endDate);
        drv.findElement(By.id("saveUButton")).click();
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("All groups");
        drv.findElement(By.cssSelector("option[value=\"All groups\"]")).click();
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits until page will be loaded completely
        setGroupID(groupName);

        return this;
    }

    public UiGroupsPage editGroup(String groupName,String newGroupName, String editedAddress, String editedCourse,
                                  String editedCapacity, String editedBoardDate, String editedStartDate,
                                  String editedStartTime, String editedEndDate) {

        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits until page will be loaded completely
        setGroupID(groupName);
        drv.findElement(By.xpath("//img[@onclick='viewEditInformationDialog(\"" + ID + "\")']")).click();
        drv.findElement(By.id("gName")).clear();
        drv.findElement(By.id("gName")).sendKeys(newGroupName);
        drv.findElement(By.id("gAddress")).clear();
        drv.findElement(By.id("gAddress")).sendKeys(editedAddress);
        new Select(drv.findElement(By.id("gCourse"))).selectByVisibleText(editedCourse);
        drv.findElement(By.id("gCapacity")).clear();
        drv.findElement(By.id("gCapacity")).sendKeys(editedCapacity);
        drv.findElement(By.id("gStartBoardingDate")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        pickDate(editedBoardDate);
        drv.findElement(By.id("gStartDate")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        pickDate(editedStartDate);
        drv.findElement(By.id("gStartTime")).clear();
        drv.findElement(By.id("gStartTime")).sendKeys(editedStartTime);
        drv.findElement(By.id("gEndDate")).click();
        drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        pickDate(editedEndDate);
        drv.findElement(By.id("saveUButton")).click();
        new Select(drv.findElement(By.id("statuses"))).selectByVisibleText("All groups");
        drv.findElement(By.cssSelector("option[value=\"All groups\"]")).click();

        return this;
    }

    public UiGroupsPage deleteGroup(String groupName) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits until page will be loaded completely
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

    public String getGroupID(String groupName) {
        String hrefID = drv.findElement(By.linkText(groupName)).getAttribute("href");
        String delStr = siteBase.toString() + "ui/group?groupID=";
        return hrefID.replace(delStr, "");
    }
}

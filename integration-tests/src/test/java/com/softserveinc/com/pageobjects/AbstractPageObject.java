package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPageObject {

    protected final WebDriver drv;
    protected final URI siteBase;

    @FindBy(css = "a[href='/']")
    protected WebElement mainLink;

    @FindBy(css = "a[href='/ui/groups']")
    protected WebElement groupsLink;

    @FindBy(css = "a[href='/ui/questions']")
    protected WebElement questionsLink;

    @FindBy(css = "a[href='/ui/users']")
    protected WebElement usersLink;

    protected AbstractPageObject(WebDriver drv, URI siteBase) {
        this.drv = drv;
        this.siteBase = siteBase;
    }

    public LogInPage logOut() {
        //
        drv.findElement(By.linkText("logOut")).click();
        //
        return new LogInPage(drv, siteBase);
    }

    public ManePage goToMain() {
        mainLink.click();
        return new ManePage(drv, siteBase);
    }

    public UiGroupsPage goToGroups() {
        groupsLink.click();
        return new UiGroupsPage(drv, siteBase);
    }

    public UiQuestionsPage goToQuestions() {
        questionsLink.click();
        return new UiQuestionsPage(drv, siteBase);
    }

    public UiUsersPage goToUsers() {
        usersLink.click();
        return new UiUsersPage(drv, siteBase);
    }

    protected String detectMonth(String month) {
        String detectedMonth = "you'll never see this!";
        switch (month) {
            case "January":   detectedMonth = "01"; break;
            case "February":  detectedMonth = "02"; break;
            case "March":     detectedMonth = "03"; break;
            case "April":     detectedMonth = "04"; break;
            case "May":       detectedMonth = "05"; break;
            case "June":      detectedMonth = "06"; break;
            case "July":      detectedMonth = "07"; break;
            case "August":    detectedMonth = "08"; break;
            case "September": detectedMonth = "09"; break;
            case "October":   detectedMonth = "10"; break;
            case "November":  detectedMonth = "11"; break;
            case "December":  detectedMonth = "12"; break;
        }

        return detectedMonth;
    }

    protected void pickDayMonth(String requestedMonth, String requestedDay) {
        String month = drv.findElement(By.cssSelector("span.ui-datepicker-month")).getText();
        String formMonth = detectMonth(month);
        if(formMonth.matches(requestedMonth)) {
            drv.findElement(By.linkText(requestedDay)).click();
        } else {

            int intFormMonth = Integer.parseInt(formMonth);
            int intRequestedMonth = Integer.parseInt(requestedMonth);

            do {
                if (intFormMonth == intRequestedMonth) {break;}
                if (intFormMonth < intRequestedMonth) {
                    drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    drv.findElement(By.cssSelector("span.ui-icon.ui-icon-circle-triangle-e")).click();
                    intFormMonth = Integer.parseInt(
                            detectMonth(drv.findElement(By.cssSelector("span.ui-datepicker-month")).getText()));
                }
                if (intFormMonth > intRequestedMonth) {
                    drv.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    drv.findElement(By.cssSelector("span.ui-icon.ui-icon-circle-triangle-w")).click();
                    intFormMonth = Integer.parseInt(
                            detectMonth(drv.findElement(By.cssSelector("span.ui-datepicker-month")).getText()));
                }
            } while (true);
            drv.findElement(By.linkText(requestedDay)).click();
        }
    }

    protected void pickDate(String requestedDate) {
        String requestedDay = requestedDate.substring(3, 5);
        if(requestedDay.startsWith("0")) {
            requestedDay = requestedDay.replace("0", "");
        }

        String requestedMonth = requestedDate.substring(0, 2); // american date stile, uh...
        String requestedYear = requestedDate.substring(6, 10);
        String formYear = drv.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
        if(formYear.matches(requestedYear)) {

            pickDayMonth(requestedMonth, requestedDay);

        } else {
            int intFormYear = Integer.parseInt(formYear);
            int intRequestedYear = Integer.parseInt(requestedYear);

            do {
                if (intFormYear == intRequestedYear) {break;}
                if (intFormYear < intRequestedYear) {
                    drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
                    drv.findElement(By.cssSelector("span.ui-icon.ui-icon-circle-triangle-e")).click();
                    intFormYear = Integer.parseInt(
                            drv.findElement(By.cssSelector("span.ui-datepicker-year")).getText());
                }
                if (intFormYear > intRequestedYear) {
                    drv.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
                    drv.findElement(By.cssSelector("span.ui-icon.ui-icon-circle-triangle-w")).click();
                    intFormYear = Integer.parseInt(
                            drv.findElement(By.cssSelector("span.ui-datepicker-year")).getText());
                }
            } while (true);

            pickDayMonth(requestedMonth, requestedDay);
        }
    }
}

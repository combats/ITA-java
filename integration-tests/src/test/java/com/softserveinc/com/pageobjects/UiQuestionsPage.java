package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class UiQuestionsPage extends AbstractPageObject{

    public static boolean isAtQuestionsPage(WebDriver drv, URI siteBase) {
        boolean res;
        res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/questions");
        if(!res) {
            res = drv.getCurrentUrl().matches(siteBase.toString() + "ui/questions#");
        }
        return res;
    }

    public UiQuestionsPage(WebDriver drv, URI siteBase) {
        super(drv, siteBase);
        if (!isAtQuestionsPage(drv, siteBase)) {
            throw new IllegalStateException("This is not questions page, current page is: " + drv.getCurrentUrl());
        }
        PageFactory.initElements(drv, this); //populates all fields @FindBy...
    }

    /**
     * Mark should be from 1 to 4
     */
    public UiQuestionsPage addQuestion(String question, String mark) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits until page will be loaded completely
        drv.findElement(By.cssSelector("span.entypo.plus-squared")).click();
        drv.findElement(By.id("qBody")).clear();
        drv.findElement(By.id("qBody")).sendKeys(question);
        drv.findElement(By.cssSelector("#qWeight > img[alt=\"" + mark + "\"]")).click();
        drv.findElement(By.id("saveQButton")).click();

        return this;
    }

    public UiQuestionsPage editQuestion(String oldQuestion, String newQuestion) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits until page will be loaded completely
        drv.findElement(By.xpath("//table[@id='selectableList']/tbody/tr/td[text()='" + oldQuestion
                                                  + "']/parent::tr/td/input")).click();
        drv.findElement(By.cssSelector("span.entypo.pencil")).click();
        drv.findElement(By.id("qBody")).clear();
        drv.findElement(By.id("qBody")).sendKeys(newQuestion);
        drv.findElement(By.id("saveQButton")).click();

        return this;
    }

    public UiQuestionsPage deleteQuestion(String question) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits until page will be loaded completely
        drv.findElement(By.xpath("//table[@id='selectableList']/tbody/tr/td[text()='" + question
                                                  + "']/parent::tr/td/input")).click();
        drv.findElement(By.id("deleteQButton")).click();
        drv.findElement(By.id("okDQButton")).click();
        return this;
    }
}

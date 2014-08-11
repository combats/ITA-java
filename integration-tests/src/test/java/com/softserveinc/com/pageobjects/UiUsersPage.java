package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.net.URI;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class UiUsersPage extends AbstractPageObject{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://176.36.11.25:3306/interview";

    static final String USER = "manager";
    static final String PASS = "javamanager";

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

    public UiUsersPage addUser(String name, String secName, String password, String email, String phone, String role) {

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
        new Select(drv.findElement(By.id("uRole"))).selectByVisibleText(role);
        drv.findElement(By.id("saveUButton")).click();

        return this;
    }

    public UiUsersPage editUser(String oldRole, String olgEmail, String name, String secName, String password, String email, String phone, String role) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        drv.findElement(By.linkText(oldRole)).click();
        String id = getUserId(olgEmail);
        String tagID = "editUser" + id;
        drv.findElement(By.xpath("//a[@id='" + tagID + "']/span/i")).click();
        if(name != null) {
            drv.findElement(By.id("uName")).clear();
            drv.findElement(By.id("uName")).sendKeys(name);
        }
        if(secName != null) {
            drv.findElement(By.id("uSurname")).clear();
            drv.findElement(By.id("uSurname")).sendKeys(secName);
        }
        if(password != null) {
            drv.findElement(By.id("uPassword")).clear();
            drv.findElement(By.id("uPassword")).sendKeys(password);
            drv.findElement(By.id("showHidePassword")).click();
        }
        if(email != null) {
            drv.findElement(By.id("uEmail")).clear();
            drv.findElement(By.id("uEmail")).sendKeys(email);
        }
        if(phone != null) {
            drv.findElement(By.id("uPhone")).clear();
            drv.findElement(By.id("uPhone")).sendKeys(phone);
        }
        if(role != null) {
            new Select(drv.findElement(By.id("uRole"))).selectByVisibleText(role);
        }
        drv.findElement(By.id("saveUButton")).click();

        return this;
    }

    public UiUsersPage deleteUser(String role, String email) {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        drv.findElement(By.linkText(role)).click();
        String id = getUserId(email);
        String tagID = "deleteUser" + id;
        drv.findElement(By.xpath("//a[@id='" + tagID + "']/span/i")).click();
        drv.findElement(By.id("okDUButton")).click();

        return this;
    }

    private String getUserId(String email) {
        String ID = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        Statement stmt = null;
        String query = "select Id from interview.Users where Email ='" + email + "'";
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ID = rs.getString("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return ID;
    }
}

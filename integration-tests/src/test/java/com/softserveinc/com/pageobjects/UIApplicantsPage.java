package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class UIApplicantsPage  extends AbstractPageObject{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://176.36.11.25:3306/interview";

    static final String USER = "manager";
    static final String PASS = "javamanager";

    private String appointmentID; // example: 4028819947789da3014778a3bf850000
    private long startTime; // example: 1406732400000 it is long

    public static boolean isAtApplicantsPage(WebDriver drv, URI siteBase, String groupID) {
        return drv.getCurrentUrl().matches(siteBase.toString() + "ui/group[?]groupID=" + groupID);
    }

    public UIApplicantsPage(WebDriver drv, URI siteBase, String groupID) {
        super(drv, siteBase);
        if (!isAtApplicantsPage(drv, siteBase, groupID)) {
            throw new IllegalStateException("This is not applicants page, current page is: " + drv.getCurrentUrl());
        }
        PageFactory.initElements(drv, this); //populates all fields @FindBy...
    }

    public String getGroupID() {
        String hrefID = drv.getCurrentUrl();
        String delStr = siteBase.toString() + "ui/group?groupID=";
        return hrefID.replace(delStr, "");
    }

    public UIApplicantsPage createApplicant(String applName, String applSurname, String phoneNum, String email,
                                            String birthDate, String pathCV) {

        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        drv.findElement(By.id("ui-id-1")).click();
        drv.findElement(By.name("name")).clear();
        drv.findElement(By.name("name")).sendKeys(applName);
        drv.findElement(By.name("surname")).clear();
        drv.findElement(By.name("surname")).sendKeys(applSurname);
        drv.findElement(By.name("phone")).clear();
        drv.findElement(By.name("phone")).sendKeys(phoneNum);
        drv.findElement(By.name("email")).clear();
        drv.findElement(By.name("email")).sendKeys(email);
        drv.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        drv.findElement(By.cssSelector(".birthday")).click();
        drv.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        pickDate(birthDate);
        drv.findElement(By.cssSelector("span.button")).click();
        drv.findElement(By.xpath("//input[@type='file']")).sendKeys(pathCV);
        drv.findElement(By.xpath("//div[@id='ui-id-2']/div/div/button")).click();

        drv.findElement(By.xpath("(//button[@type='button'])[2]")).click(); //alert

        return this;
    }

    public UIApplicantsPage setInterviewDate(String applName, String applSurname, String emaile) throws InterruptedException {

        JavascriptExecutor js = ((JavascriptExecutor) drv);

        js.executeScript("$('h3 span').each(function(index, element) {\n" +
                "    var name = $(element).text().split(/\\s+/)[1];\n" +
                "    if (name === '" + applName + " " + applSurname + "') {\n" +
                "        $(element).click();\n" +
                "    };\n" +
                "});");

        setStartTime();
        String applId = getApplicantId(emaile);
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//        drv.findElement(By.xpath("//h3[@id='ui-id-3']/span[2]")).click();
//        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        drv.findElement(By.cssSelector(".date.schedulable")).click();
//        drv.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
//        pickDate(getTomorrowsDate());
//        drv.findElement(By.cssSelector(".duration")).clear();
//        drv.findElement(By.cssSelector(".duration")).sendKeys("30");
//        drv.findElement(By.cssSelector(".time")).clear();
//        drv.findElement(By.cssSelector(".time")).sendKeys("12:30");
//        drv.findElement(By.cssSelector("#ui-id-6-button > span.ui-icon.ui-icon-triangle-1-s")).click();
        String id = drv.findElement(By.xpath("//div[@applicantid='" + applId + "']")).getAttribute("id");
        System.out.println(id);
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String spanClass = drv.findElement(By.xpath("//div[@applicantid='" + applId + "']/div[2]/span[1]")).getAttribute("class");
        System.out.println(spanClass);
//        drv.findElement(By.id("ui-id-11")).click();
//        drv.findElement(By.xpath("//div[@id='ui-id-4']/div[2]/button")).click();
//        drv.findElement(By.cssSelector("#ui-id-6-button > span.ui-icon.ui-icon-triangle-1-s")).click();
//        drv.findElement(By.id("ui-id-17")).click();
//        drv.findElement(By.xpath("//div[@id='ui-id-4']/div[2]/button")).click();
//        drv.findElement(By.xpath("//div[@id='ui-id-4']/div[2]/div/button")).click();
//        drv.findElement(By.xpath("(//button[@type='button'])[2]")).click();
//
//        setAppointmentDate(applId, startTime);

        return this;
    }

    private void setAppointmentDate(String applicantID, long newStartTime) {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        Statement stmt = null;
        String query = "update interview.Appointments set StartTime ='" + newStartTime + "' where ApplicantId ='" + applicantID + "'";
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = connection.createStatement();

            stmt.executeUpdate(query);

            String sql = "SELECT StartTime FROM interview.Appointments where ApplicantId ='" + applicantID + "'";
            ResultSet rs = stmt.executeQuery(sql);


//            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long ID = rs.getLong("StartTime");
                System.out.println(ID);
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
    }


    public String getTomorrowsDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return dateFormat.format(cal.getTime());
    }

    private void setStartTime() {
        startTime = System.currentTimeMillis(); // or add 15 minutes, or 15*60*1000
    }

    private String getApplicantId(String email) {
        String ID = null;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        Connection connection = null;
        Statement stmt = null;
        String query = "select Id from interview.Applicants where Email ='" + email + "'";
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

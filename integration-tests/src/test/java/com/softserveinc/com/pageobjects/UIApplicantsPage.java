package com.softserveinc.com.pageobjects;

import org.openqa.selenium.By;
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

//    @FindBy(css = "a[href='/']")
//    private WebElement fileUpload;

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
//        String hrefID = drv.findElement(By.linkText(groupName)).getAttribute("href");
        String hrefID = drv.getCurrentUrl();
        String delStr = siteBase.toString() + "ui/group?groupID=";
        return hrefID.replace(delStr, "");
    }

    public UIApplicantsPage createApplicant(String applName, String applSurname, String phoneNum, String email,
                                            String birthDate, String pathCV) {
//        drv.findElement(By.partialLinkText("[New applicant]")).click();
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

//        WebDriverWait wait = new WebDriverWait(drv, 10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button'][2]")));

//        WebElement myDynamicElement = (new WebDriverWait(drv, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));

        return this;
    }

    public UIApplicantsPage setInterviewDate() {
        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        drv.findElement(By.xpath("//h3[@id='ui-id-10']/span[2]")).click();

        drv.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        drv.findElement(By.cssSelector(".date.schedulable")).click();
        drv.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        pickDate(getTomorrowsDate()); //something wrong
        drv.findElement(By.cssSelector(".duration")).clear();
        drv.findElement(By.cssSelector(".duration")).sendKeys("30");
        drv.findElement(By.cssSelector(".time")).clear();
        drv.findElement(By.cssSelector(".time")).sendKeys("12:30");
        drv.findElement(By.cssSelector("#ui-id-6-button > span.ui-icon.ui-icon-triangle-1-s")).click();
        drv.findElement(By.id("ui-id-11")).click();
        drv.findElement(By.xpath("//div[@id='ui-id-4']/div[2]/button")).click();
        drv.findElement(By.cssSelector("#ui-id-6-button > span.ui-icon.ui-icon-triangle-1-s")).click();
        drv.findElement(By.id("ui-id-17")).click();
        drv.findElement(By.xpath("//div[@id='ui-id-4']/div[2]/button")).click();
        drv.findElement(By.xpath("//div[@id='ui-id-4']/div[2]/div/button")).click();
        drv.findElement(By.xpath("(//button[@type='button'])[2]")).click();

        drv.findElement(By.xpath("//h3[@id='ui-id-10']/span[2]")).click();
//        setAppointmentID();
        setAppointmentDate();

        return this;
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

    private void setAppointmentID() {
        String ID = drv.findElement(By.xpath("//div[@class='schedule']")).getAttribute("appointmentid");
        System.out.println(ID);
//        appointmentID = drv.findElement(By.xpath("//div[@class='schedule']")).getAttribute("appointmentid");
        appointmentID = ID;
    }

    private void setAppointmentDate() {
        Connection conn = null;
        Statement stmt = null;

        { // populating some fields
            setStartTime();
            setAppointmentID();
        }

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "UPDATE interview.Appointments SET StartTime='" + startTime + "' WHERE Id='" + appointmentID + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }
}

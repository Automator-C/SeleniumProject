package com.uiweb.test.dashboard;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.DashboardPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ValidateIssueCount {

    final static Logger logger = Logger.getLogger(ValidateIssueCount.class);
    private WebDriver driver;
    DashboardPage dashboardPage;

    @BeforeTest
    public void setUp(){
        driver = DriverManager.openBrowser("chrome");
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
        dashboardPage = new DashboardPage(driver);

    }

    @Test
    public void testIssueCount(){

        int size = dashboardPage.getIssueRecordCount();
        System.out.println("Issue list size is "+size);
        Assert.assertEquals(size, 3);
        Assert.assertTrue( size < 4, "Size is less than 4");
        Assert.assertFalse( size > 3, "Size is not greater than 3");
    }


    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }

}
package com.uiweb.test.dashboard;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.DashboardPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import org.testng.annotations.*;

public class ValidateEditDeleteButton {

    final static Logger logger = Logger.getLogger(ValidateEditDeleteButton.class);
    private WebDriver driver;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp(){
        //driver = DriverManager.openBrowser("chrome");
        DriverManager.openBrowser("chrome");
        driver = DriverManager.getDriver();
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
        dashboardPage = new DashboardPage(driver);

    }

    @Test
    public void testEditDeleteButtonPresent(){
        logger.info(" Thread count - testEditDeleteButtonPresent - "+Thread.currentThread().threadId());
        boolean editBtn = dashboardPage.isEditBtnDisplayed();
        System.out.println("Edit button status is "+editBtn);
        Assert.assertTrue(editBtn,"Edit button is present");
        boolean deleteButton = dashboardPage.isDeleteBtnDisplayed();
        System.out.println("Delete button status is "+deleteButton);
        Assert.assertTrue(deleteButton,"Delete button is present");
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();
    }
}

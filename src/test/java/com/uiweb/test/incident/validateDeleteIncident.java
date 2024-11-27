package com.uiweb.test.incident;

import com.aventstack.extentreports.ExtentTest;
import com.uiweb.driver.DriverManager;
import com.uiweb.pages.AddIncidentPage;
import com.uiweb.pages.DashboardPage;
import com.uiweb.reportUtils.TestListener;
import com.uiweb.utils.ConfigManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class validateDeleteIncident {

    /**Verify that an issue can be deleted, and it is removed from the incident list
     */
    final static Logger logger = Logger.getLogger(validateDeleteIncident.class);
    private WebDriver driver;
    DashboardPage dashboardPage;
    ExtentTest test;
    private static String issueTitle = "Database Connection Error";
    private final String browserName = ConfigManager.getProperty("browser");

    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        logger.info("Setup step");
        //test = TestListener.getExtentTest();
        DriverManager.openBrowser(browserName);
        driver = DriverManager.getDriver();
        DriverManager.goToUrl(ConfigManager.getProperty("appUrl"));
        dashboardPage = new DashboardPage(driver);


    }

    @Test
    public void validateDeleteIncident() throws InterruptedException {
        //test = TestListener.getExtentTest();
        dashboardPage.getRowToDeleteAndClickOnIt(issueTitle);
        boolean isDeleted = dashboardPage.validateDeletedRecord(issueTitle);
        Thread.sleep(1000);
        Assert.assertTrue(isDeleted, "Record was deleted");
        //test.info("Delete Incident functionality validated with success");

    }


    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

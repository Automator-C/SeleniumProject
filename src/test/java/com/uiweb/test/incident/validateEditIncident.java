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

public class validateEditIncident {
    /**Validate that user should be able to edit the incident form.
     Validate that the newly edited data is available in the dashboard screen
     */
    final static Logger logger = Logger.getLogger(validateEditIncident.class);
    private WebDriver driver;
    DashboardPage dashboardPage;
    AddIncidentPage addIncidentPage;
    ExtentTest test;
    private static String issueTitle = "Database Connection Error";
    private static String priority = "High";
    private static String status = "Open";
    private final String browserName = ConfigManager.getProperty("browser");

    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        //test = TestListener.getExtentTest();
        logger.info("Setup step");
        DriverManager.openBrowser(browserName);
        driver = DriverManager.getDriver();
        DriverManager.goToUrl(ConfigManager.getProperty("appUrl"));
        dashboardPage = new DashboardPage(driver);
        addIncidentPage = new AddIncidentPage(driver);
    }

    @Test
    public void validateEditIncident() throws InterruptedException {
        //test = TestListener.getExtentTest();
        dashboardPage.getRowToEditAndClickOnIt(issueTitle);
        addIncidentPage.selectPriorityOption(priority);
        addIncidentPage.selectStatusOption(status);
        addIncidentPage.clickOnSaveIncidentButton();
        dashboardPage.isEditBtnDisplayed();
        Thread.sleep(1000);
        String actualPriority = dashboardPage.getPriorityBasedOnTitleName(issueTitle);
        String actualStatus = dashboardPage.getStatusBasedOnTitleName(issueTitle);
        Assert.assertEquals(actualPriority,priority);
        Assert.assertEquals(actualStatus,status);
        // test.info("Edit Incident functionality validated with success");
    }


    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

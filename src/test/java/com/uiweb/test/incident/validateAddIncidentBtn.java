package com.uiweb.test.incident;

import com.aventstack.extentreports.ExtentTest;
import com.uiweb.driver.DriverManager;
import com.uiweb.pages.AddIncidentPage;
import com.uiweb.pages.DashboardPage;
import com.uiweb.reportUtils.TestListener;
import com.uiweb.test.dashboard.Validate_Dashboard;
import com.uiweb.utils.ConfigManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class validateAddIncidentBtn {

    /**Validate that when "Add Incident" button is clicked, it opens up in an add incident form.
     And on Click of "Dashboard" icon, user navigates back to Dashboard page
     */
    final static Logger logger = Logger.getLogger(validateAddIncidentBtn.class);
    private WebDriver driver;

    DashboardPage dashboardPage;
    AddIncidentPage addIncidentPage;
    ExtentTest test;
    private final String browserName = ConfigManager.getProperty("browser");

    @BeforeMethod
    public void setUp(){
        logger.info("Setup step");
        DriverManager.openBrowser(browserName);
        driver = DriverManager.getDriver();
        DriverManager.goToUrl(ConfigManager.getProperty("appUrl"));
        dashboardPage = new DashboardPage(driver);
        addIncidentPage = new AddIncidentPage(driver);
    }

    @Test
    public void validateAddIncidentBtnFunctionality(){
        //test = TestListener.getExtentTest();
        dashboardPage.clickOnIncidentBtn();
        String incidentHeaderText = addIncidentPage.getIncidentHeader();
        Assert.assertEquals(incidentHeaderText,"Add New Incident");
        //test.info("Add New Incident Button is available in the dashboard page");
        dashboardPage.clickOnDashboardIcon();
        String headerText = dashboardPage.getDashboardHeaderText();
        Assert.assertEquals(headerText, "Incident Dashboard");
        //test.info("Incident Dashboard header is available in the dashboard page");

    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();

    }



}

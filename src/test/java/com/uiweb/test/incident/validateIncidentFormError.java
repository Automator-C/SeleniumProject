package com.uiweb.test.incident;

import com.aventstack.extentreports.ExtentTest;
import com.uiweb.driver.DriverManager;
import com.uiweb.pages.AddIncidentPage;
import com.uiweb.pages.DashboardPage;
import com.uiweb.reportUtils.TestListener;
import com.uiweb.utils.ConfigManager;
import com.uiweb.utils.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class validateIncidentFormError {

    /**Validate that "Add Incident" form shows field level error messages when the user tries to submit
     *  the form without entering all the required fields
     */
    final static Logger logger = Logger.getLogger(validateIncidentFormError.class);
    private WebDriver driver;

    DashboardPage dashboardPage;
    AddIncidentPage addIncidentPage;
    ExtentTest test;
    private final String browserName = ConfigManager.getProperty("browser");

    @BeforeMethod
    public void setUp(){
        DriverManager.openBrowser(browserName);
        driver = DriverManager.getDriver();
        DriverManager.goToUrl(ConfigManager.getProperty("appUrl"));
        dashboardPage = new DashboardPage(driver);
        addIncidentPage = new AddIncidentPage(driver);
    }

    @Test
    public void validateIncidentFormErrors(){
        //test = TestListener.getExtentTest();
        dashboardPage.clickOnIncidentBtn();
        addIncidentPage.clickOnIncidentBtn();
        Assert.assertTrue(addIncidentPage.isTitleErrorDisplayed(), Constants.TITLE_ERROR_MESSAGE);
        Assert.assertTrue(addIncidentPage.isDescErrorDisplayed(), Constants.DESC_ERROR_MESSAGE);
        Assert.assertTrue(addIncidentPage.isCreatedByrDisplayed(), Constants.CREATEDBY_ERROR_MESSAGE);
        Assert.assertTrue(addIncidentPage.isAssignedToErrorDisplayed(), Constants.ASSIGNEDTO_ERROR_MESSAGE);
        //test.info("All the error messages are displayed as expected.");
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }
}

package com.uiweb.test.incident;

import com.aventstack.extentreports.ExtentTest;
import com.uiweb.driver.DriverManager;
import com.uiweb.pages.AddIncidentPage;
import com.uiweb.pages.DashboardPage;
import com.uiweb.reportUtils.TestListener;
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

    @BeforeMethod
    public void setUp(ITestContext context){
        driver = DriverManager.openBrowser("chrome");
        context.setAttribute("WebDriver", driver);
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
        dashboardPage = new DashboardPage(driver);
        addIncidentPage = new AddIncidentPage(driver);
    }

    @Test
    public void validateIncidentFormErrors(){
        test = TestListener.getExtentTest();
        dashboardPage.clickOnIncidentBtn();
        addIncidentPage.clickOnIncidentBtn();
        Assert.assertTrue(addIncidentPage.isTitleErrorDisplayed(), "Title field error is displayed");
        Assert.assertTrue(addIncidentPage.isDescErrorDisplayed(), "Description field error is displayed");
        Assert.assertTrue(addIncidentPage.isCreatedByrDisplayed(), "CreatedBy field error is displayed");
        Assert.assertTrue(addIncidentPage.isAssignedToErrorDisplayed(), "AssignedTo field error is displayed");
        test.info("All the error messages are displayed as expected.");

    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }
}

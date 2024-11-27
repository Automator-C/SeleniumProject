package com.uiweb.test.incident;

import com.aventstack.extentreports.ExtentTest;
import com.uiweb.driver.DriverManager;
import com.uiweb.pages.AddIncidentPage;
import com.uiweb.pages.DashboardPage;
import com.uiweb.reportUtils.TestListener;
import com.uiweb.utils.ConfigManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class validateIncidentSubmission {

    /**Validate that user should be able to submit the form, once all the required data is entered.
     Validate that the newly created data is available in the dashboard screen
     */
    final static Logger logger = Logger.getLogger(validateIncidentSubmission.class);
    private WebDriver driver;
    DashboardPage dashboardPage;
    AddIncidentPage addIncidentPage;
    ExtentTest test;
    String titleText = "Network Issue";
    private final String browserName = ConfigManager.getProperty("browser");

    @BeforeMethod
    public void setUp(ITestContext context){
        logger.info("Setup step");
        DriverManager.openBrowser(browserName);
        driver = DriverManager.getDriver();
        DriverManager.goToUrl(ConfigManager.getProperty("appUrl"));
        dashboardPage = new DashboardPage(driver);
        addIncidentPage = new AddIncidentPage(driver);
    }

    @Test
    public void validateSubmitIncident() throws InterruptedException {
        //test = TestListener.getExtentTest();
        dashboardPage.clickOnIncidentBtn();
        addIncidentPage.enterTitle(titleText);
        addIncidentPage.enterDescription("Cannot connect to internet");
        addIncidentPage.selectAssignedToOption("Jane Smith");
        addIncidentPage.enterCreatedByField("Shelly Johnson");
        addIncidentPage.selectPriorityOption("Medium");
        addIncidentPage.selectStatusOption("Open");
        addIncidentPage.clickOnIncidentBtn();
        dashboardPage.isEditBtnDisplayed();
        Thread.sleep(1000);
        dashboardPage.isIncidentPresentInDashboard(titleText);
        //test.info("New incident is added successfully");

    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();

    }

}

package com.uiweb.test.dashboard;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.uiweb.driver.DriverManager;
import com.uiweb.pages.DashboardPage;
import com.uiweb.reportUtils.ReportManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class Validate_Dashboard extends ReportManager {

    final static Logger logger = Logger.getLogger(Validate_Dashboard.class);
    private WebDriver driver;

    DashboardPage dashboardPage;

    private ExtentTest extentTest;

    @BeforeMethod
    public void setUp(ITestContext context){
        driver = DriverManager.openBrowser("chrome");
        context.setAttribute("WebDriver", driver);
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
        dashboardPage = new DashboardPage(driver);

    }

    @Test(description = "To validate the presence of Dashboard Icon")
    public void validateDashboardIcon(){
        //extentTest = extent.createTest("To validate the presence of Dashboard Icon");
        String iconName = dashboardPage.getDashboardIconText();
        System.out.println("Icon name is "+iconName);
        logger.info("Icon name is "+iconName);
        Assert.assertEquals(iconName, "Dashboardss");

        //extentTest.log(Status.PASS, MarkupHelper.createLabel("Validation pass", ExtentColor.GREEN));
    }

    @Test(description = "To validate the presence of Add Incident Button")
    public void validateAddIncidentBtn(){
       // extentTest = extent.createTest("To validate the presence of Add Incident Button");
        String incidentBtn = dashboardPage.getIncidentBtnText();
        System.out.println("Incident Btn text is "+incidentBtn);
        logger.info("Incident Btn text is "+incidentBtn);
        Assert.assertEquals(incidentBtn, "Add Incident");
       // extentTest.log(Status.FAIL, MarkupHelper.createLabel("Validation failed", ExtentColor.RED));

    }
    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }
}

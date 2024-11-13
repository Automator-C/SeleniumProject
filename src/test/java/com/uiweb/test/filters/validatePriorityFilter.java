package com.uiweb.test.filters;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.FilterOptionsPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class validatePriorityFilter {

    final static Logger logger = Logger.getLogger(validatePriorityFilter.class);
    private WebDriver driver;
    FilterOptionsPage filterOptionsPage;

    @BeforeMethod
    public void setUp(ITestContext context) throws MalformedURLException, InterruptedException {
        driver = DriverManager.openBrowser("chrome");
        context.setAttribute("WebDriver", driver);
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
        filterOptionsPage = new FilterOptionsPage(driver);
    }
    @Test
    public void validateFilterForHighPriorityDD() throws InterruptedException {
        filterOptionsPage.selectPriorityFilter("High");
        filterOptionsPage.validateFilteredPriority("High");
    }

    @Test
    public void validateFilterForMediumPriorityDD() throws InterruptedException {
        filterOptionsPage.selectPriorityFilter("Medium");
        filterOptionsPage.validateFilteredPriority("Medium");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }


}
package com.uiweb.test.filters;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.FilterOptionsPage;
import com.uiweb.utils.ConfigManager;
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
    private final String browserName = ConfigManager.getProperty("browser");

    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        logger.info("Setup step");
        DriverManager.openBrowser(browserName);
        driver = DriverManager.getDriver();
        DriverManager.goToUrl(ConfigManager.getProperty("appUrl"));
        filterOptionsPage = new FilterOptionsPage(driver);
    }

    @Test
    public void validateFilterForHighPriorityDD() throws InterruptedException {
        filterOptionsPage.selectPriorityFilter("High");
        filterOptionsPage.validateFilteredPriority("High");
        logger.info("filter option with high priority was validated");
    }

    @Test
    public void validateFilterForMediumPriorityDD() throws InterruptedException {
        filterOptionsPage.selectPriorityFilter("Medium");
        filterOptionsPage.validateFilteredPriority("Medium");
        logger.info("filter option with medium priority was validated");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }


}

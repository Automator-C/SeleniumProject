package com.uiweb.test.filters;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.DashboardPage;
import com.uiweb.pages.FilterOptionsPage;
import com.uiweb.utils.ConfigManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class validateStatusFilter {

    final static Logger logger = Logger.getLogger(validateStatusFilter.class);
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
    public void validateFilterForStatusDropDown() throws InterruptedException {
        filterOptionsPage.selectStatusFilter("Open");
        filterOptionsPage.validateFilteredStatus("Open");
    }

    @Test
    public void validateFilterForAllStatus() throws InterruptedException {
        filterOptionsPage.selectStatusFilter("Open");
        filterOptionsPage.clickToSelectAllStatusFilter();
        filterOptionsPage.validateAllStatuses();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }


}

/*(package com.uiweb.test.filters;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.DashboardPage;
import com.uiweb.pages.FilterOptionsPage;
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

    @BeforeMethod
    public void setUp(ITestContext context) throws MalformedURLException, InterruptedException {
        driver = DriverManager.openBrowser("chrome");
        context.setAttribute("WebDriver", driver);
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
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
 */

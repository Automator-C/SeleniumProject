package com.uiweb.test.dashboard;

import com.uiweb.driver.DriverManager;
import com.uiweb.pages.DashboardPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ValidateDashboardTable {

    final static Logger logger = Logger.getLogger(ValidateDashboardTable.class);
    private WebDriver driver;
    DashboardPage dashboardPage;

    @BeforeTest
    public void setUp(){
        driver = DriverManager.openBrowser("chrome");
        DriverManager.goToUrl("https://incidentmgmt.savitools.com");
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void testTableHeaders(){
          List<WebElement> headers = dashboardPage.getTableHeaders() ;

        String[] expectedHeaders = {"Title", "Description","Assigned To", "Priority","Status","Created By","Created At","Updated At"};

        boolean headersMatch = true;
        for(int i=0; i<expectedHeaders.length;i++){
            String actualHeaders = headers.get(i).getText().trim();
            if(!actualHeaders.equals(expectedHeaders[i])){
                headersMatch = false;
                System.out.println("Header mismatch: Expected '"+expectedHeaders[i]+"'but found "+actualHeaders+ "'");
            }
            //System.out.println("Actual headers are: "+actualHeaders);
        }
        // Print the result
        if (headersMatch) {
            System.out.println("Test Passed: All table headers are correct.");
        } else {
            System.out.println("Test Failed: One or more table headers are incorrect.");
        }
    }



    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }

}
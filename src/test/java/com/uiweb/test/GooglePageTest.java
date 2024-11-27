package com.uiweb.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GooglePageTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
    }

    @Test
    public void testGooglePageTitle(){

        driver.get("https://www.google.com");

        boolean isGooglePageOpen = driver.getTitle().contains("Google");
        Assert.assertTrue(isGooglePageOpen, "Google was opened successfully");

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

package com.uiweb.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;

import java.time.Duration;


public class DriverManager {

    final static Logger logger = Logger.getLogger(DriverManager.class);
    //public static WebDriver driver;

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void openBrowser(String browserName) {
        WebDriver webDriver=null;
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("test-type");
            options.addArguments("--networkConnectionEnabled=true");
            options.setAcceptInsecureCerts(true);
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--enable-automation");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new");
            options.addArguments("--whitelisted-ips"); //bypass whitelisted IPs
            logger.info("Running chrome browser");
            webDriver = new ChromeDriver(options);
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            logger.info("Running firefox browser");
            webDriver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            logger.info("Running edge browser");
            webDriver = new EdgeDriver();

        } else if (browserName.equalsIgnoreCase("ie")) {
            logger.info("Running internet explorer browser");
            webDriver = new InternetExplorerDriver();

        } else if (browserName.equalsIgnoreCase("safari")) {
            logger.info("Running safari browser");
            webDriver = new SafariDriver();
        }
        driver.set(webDriver);
    }

    public static void goToUrl(String appUrl) {
        logger.info("Getting the application url " + appUrl);
        WebDriver driver = getDriver(); //get Thread-safe Webdriver instance
        if (driver != null) {
            driver.get(appUrl);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        } else {
            throw new IllegalStateException("WebDriver has not been initialized.");
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    @AfterClass
    public void tearDownClass() {
        DriverManager.quitDriver();  // Closes browser after all tests in the class finish
    }



}

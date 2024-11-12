package com.uiweb.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;


public class DriverManager {

    final static Logger logger = Logger.getLogger(DriverManager.class);
    public static WebDriver driver;

    public static WebDriver openBrowser(String browserName){

        if(browserName.equalsIgnoreCase("chrome")){
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
            driver = new ChromeDriver(options);


        }else if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();

        }else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();

        }else if(browserName.equalsIgnoreCase("ie")){
            driver = new InternetExplorerDriver();

        }

        return driver;
    }

        public static void goToUrl(String appUrl){
        logger.info("Getting the application url "+appUrl);
        driver.get(appUrl);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        }


}

package com.uiweb.reportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static String dirName = new SimpleDateFormat("YYYY-MM_dd_HH-mm-ss").format(new Date());
    private static ExtentReports extent = ExtentManager.createInstance("./reports/"+dirName+"/extent-report.html");
    //private static ExtentReports extent = ExtentManager.createInstance("./reports/extent-report.html");

    public static ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        test.info("Test started for "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentManager.getTest().pass("Test Passed for "+result.getMethod().getMethodName());

        // Capture and log the screenshot
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
        ExtentManager.getTest().pass("Screenshot of success", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

    }

    //    @Override
//    public void onTestFailure(ITestResult result) {
//        ExtentManager.getTest().fail(result.getThrowable());
//    }
    @Override
    public void onTestFailure(ITestResult result) {
        // Log the failure with the exception
        ExtentManager.getTest().fail(result.getThrowable());

        // Capture and log the screenshot
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
        ExtentManager.getTest().fail("Screenshot of failure", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.flush();
    }

    // Screenshot method
    private String takeScreenshot(String methodName, WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/" + methodName + ".png";
        try {
            Files.createDirectories(Paths.get("./reports/"+dirName+"/screenshots"));
            Files.copy(screenshot.toPath(), Paths.get("./reports/"+dirName+"/"+screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public static ExtentTest getExtentTest() {

        return test;
    }
}

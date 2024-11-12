package com.uiweb.reportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.uiweb.utils.ConfigFileUtil;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    final static Logger logger = Logger.getLogger(ReportManager.class);

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public String extentReportPath;

    @BeforeTest(alwaysRun=true)
    public void reportSetUp() {
        logger.info("Inside Report Manager");
        extentReportPath= ConfigFileUtil.fileWithDirectory("reports/");
        sparkReporter = new ExtentSparkReporter (extentReportPath + "Incident Mgmt Test Report_" + new SimpleDateFormat("MM_dd_yyyy").format(new Date())+".html" );
        sparkReporter.config().setDocumentTitle("INCIDENT MGMT TEST REPORT");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("ENVIRONMENT", "TEST");
    }

    @AfterTest
    public void reportEnd() {
        logger.info("Report");
        extent.flush();
    }

}

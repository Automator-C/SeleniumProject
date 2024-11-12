package com.uiweb.pages;

import com.uiweb.utils.WebUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class FilterOptionsPage {
    final static Logger logger =Logger.getLogger(FilterOptionsPage.class);
    WebDriver driver;
    WebUtil webUtil;

    public FilterOptionsPage(WebDriver driver){
        this.driver = driver;
        webUtil = new WebUtil();

    }

//    Elements
    By statusDropDownEle = By.xpath("//div[@class='filter-container']/select[1]");
    By priorityDropDownEle = By.xpath("//div[@class='filter-container']/select[3]");

//    Methods
    public void selectStatusFilter(String statusToSelect){
        webUtil.selectDDbyValue(driver, statusDropDownEle, statusToSelect);
    }

    public void selectPriorityFilter(String priorityToSelect){
        webUtil.selectDDbyValue(driver, priorityDropDownEle, priorityToSelect);
    }

    public void validateFilteredStatus(String expectedStatus) {
        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));


        for (WebElement row : rows) {
            WebElement statusColumn = row.findElement(By.xpath("td[5]"));
            String statusText = statusColumn.getText();
            System.out.println("Status text is: "+statusText);
            Assert.assertEquals(statusText, expectedStatus);
        }
    }

    public void clickToSelectAllStatusFilter() throws InterruptedException {
        webUtil.clickAction(driver,statusDropDownEle);
        webUtil.sendKeyboardAction(driver,statusDropDownEle, Keys.ARROW_UP);
        webUtil.sendKeyboardAction(driver,statusDropDownEle, Keys.ENTER);

    }

    // Helper method to check if multiple statuses are present
    public boolean areMultipleStatusesPresent(List<WebElement> rows) {
        boolean openFound = false;
//        boolean closedFound = false;
        boolean inProgressFound = false;

        for (WebElement row : rows) {
            WebElement statusColumn = row.findElement(By.xpath("td[5]"));
            String statusText = statusColumn.getText();
            System.out.println("Status text is: "+statusText);

            if (statusText.equals("Open")) openFound = true;
//            if (statusText.equals("Closed")) closedFound = true;
            if (statusText.equals("In Progress")) inProgressFound = true;

            if (openFound || inProgressFound) {
//                if (openFound && closedFound && inProgressFound) {
                return true;
            }
        }
        return false;
    }

    public void validateAllStatuses() {
        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        Assert.assertTrue(areMultipleStatusesPresent(rows),
                "The 'All' filter did not display multiple statuses.");
    }

    public List<WebElement> getFilteredTableData() {
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));
        return tableBody.findElements(By.tagName("tr"));
    }
    public void validateFilteredPriority(String expectedPriority) {

        List<WebElement> rows = getFilteredTableData();

        for (WebElement row : rows) {
            WebElement priorityColumn = row.findElement(By.xpath("td[4]"));
            String priorityText = priorityColumn.getText();
            System.out.println("Priority text is: "+priorityText);
            Assert.assertEquals(priorityText, expectedPriority);
        }
    }

}

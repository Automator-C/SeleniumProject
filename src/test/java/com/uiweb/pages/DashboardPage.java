package com.uiweb.pages;

import com.uiweb.utils.WebUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage {
    final static Logger logger = Logger.getLogger(DashboardPage.class);

    WebDriver driver;
    WebUtil webUtils;
    public DashboardPage(WebDriver driver){
        this.driver = driver;
        webUtils = new WebUtil();
    }

    //WebElements
    By dashboardEle = By.xpath("//div[@class='navleft']/h1");
    By incidentEle = By.xpath("//div[@class='navright']/button/a");

    By editBtnEle = By.cssSelector("td > button.edit-button > a");
    By deleteBtnEle =By.cssSelector("td > button.delete-button >svg");

    By issueList = By.xpath("//table/tbody/tr");

    By tableHeaders = By.cssSelector("table thead tr th");
    By dashboardHeaderEle = By.xpath("//div[@class='header']/h5");

    public String getDashboardIconText(){

        return webUtils.elementGetText(driver, dashboardEle);
    }

    public String getIncidentBtnText(){
        return webUtils.elementGetText(driver, incidentEle);
    }

    public boolean isEditBtnDisplayed(){
        return webUtils.isElementDisplayed(driver,editBtnEle);
    }

    public boolean isDeleteBtnDisplayed(){
        return webUtils.isElementDisplayed(driver,deleteBtnEle);
    }

    public int getIssueRecordCount(){
        List<WebElement> issueCount = webUtils.elements(driver,issueList);
        return issueCount.size();
    }

    public List<WebElement> getTableHeaders(){

        return webUtils.elements(driver,tableHeaders);
    }

    public void clickOnIncidentBtn() {
        webUtils.clickAction(driver,incidentEle);
    }

    public void clickOnDashboardIcon() {
        webUtils.clickAction(driver, dashboardEle);
    }

    public String getDashboardHeaderText() {
        return webUtils.elementGetText(driver, dashboardHeaderEle);
    }

    public boolean isIncidentPresentInDashboard(String title){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement incidentRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[td[text()='" + title + "']]")));
        return incidentRow !=null;
    }

    /**
     * Click on edit button based on the desired title
     *
     */
    public void getRowToEditAndClickOnIt(String titleToFind) {

        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            // Get the title column text
            WebElement titleColumn = row.findElement(By.xpath("td[1]")); // The title is in the first column
            String titleText = titleColumn.getText();

            if (titleText.equals(titleToFind)) {
                // Found the matching title, now find the edit button in the same row
                WebElement editButton = row.findElement(By.xpath("td[9]/button/a")); // Edit button is in the 9th column
                editButton.click();
                break;
            }
        }
    }

    /**
     * Get priority based on title name
     *
     */
    public String getPriorityBasedOnTitleName(String titleToFind) {
        String priorityText = null;
        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            // Get the title column text
            WebElement titleColumn = row.findElement(By.xpath("td[1]"));
            String titleText = titleColumn.getText();

            if (titleText.equals(titleToFind)) {
                // Found the matching title, now find the priority column in the same row
                priorityText = row.findElement(By.xpath("td[4]")).getText();

                break;
            }
        }
        return priorityText;
    }
    /**
     * Get status based on title name
     *
     */
    public String getStatusBasedOnTitleName(String titleToFind) {
        String statusText = null;
        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            // Get the title column text
            WebElement titleColumn = row.findElement(By.xpath("td[1]"));
            String titleText = titleColumn.getText();

            if (titleText.equals(titleToFind)) {
                // Found the matching title, now find the status column in the same row
                statusText = row.findElement(By.xpath("td[5]")).getText();

                break;
            }
        }
        return statusText;
    }

    /**
     * Click on delete button based on the desired title
     *
     */
    public void getRowToDeleteAndClickOnIt(String titleToDelete) {

        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        boolean recordFound = false;
        for (WebElement row : rows) {
            // Get the title column text
            WebElement titleColumn = row.findElement(By.xpath("td[1]"));
            String titleText = titleColumn.getText();

            if (titleText.equals(titleToDelete)) {
                recordFound = true;
                // Found the matching title, now find the delete button in the same row
                WebElement deleteButton = row.findElement(By.xpath("td[10]/button"));
                deleteButton.click();
                break;
            }
        }
    }

    /**
     * Verify record was deleted by searching for matching title
     *
     */
    public boolean validateDeletedRecord(String titleToDelete) {

        // Locate the table body
        WebElement tableBody = driver.findElement(By.xpath("//table/tbody"));

        // Get all rows in the table body
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));

        boolean isDeleted = true;
        for (WebElement row : rows) {
            // Get the title column text
            WebElement titleColumn = row.findElement(By.xpath("td[1]"));
            String titleText = titleColumn.getText();

            if (titleText.equals(titleToDelete)) {
                isDeleted = false;
                break;
            }
        }
        return isDeleted;
    }


}

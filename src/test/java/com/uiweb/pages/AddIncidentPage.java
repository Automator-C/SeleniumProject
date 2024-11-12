package com.uiweb.pages;

import com.uiweb.utils.WebUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddIncidentPage {

    final static Logger logger = Logger.getLogger(AddIncidentPage.class);

    WebDriver driver;
    WebUtil webUtils;
    public AddIncidentPage(WebDriver driver){
        this.driver = driver;
        webUtils = new WebUtil();
    }
    By incidentHeader = By.xpath("//*[@class='add-incident']/h2");
    By titleField = By.xpath("//input[@name='title']");

    By descriptionField = By.xpath("//textarea[@name='description']");

    By assignedToField = By.xpath("//select[@name='assignedTo']");

    By createdByField = By.xpath("//input[@name='createdBy']");

    By priorityField = By.xpath("//select[@name='priority']");

    By statusField = By.xpath("//select[@name='status']");

    By incidentBtn = By.xpath("//button[text()='Add Incident']");

    By titleFieldError=By.xpath("//span[text()='Title is required']");

    By descriptionFieldError=By.xpath("//span[text()='Description is required']");

    By assignedToFieldError = By.xpath("//div[text()='Assigned To is required']");

    By createdByFieldError = By.xpath("//div[text()='Created By is required']");

    By saveIncidentBtn = By.xpath("//button[text()='Save Incident']");
    public String getIncidentHeader() {
        return webUtils.elementGetText(driver,incidentHeader);
    }


   public boolean isTitleErrorDisplayed(){
        return webUtils.isElementDisplayed(driver, titleFieldError);

   }

   public boolean isDescErrorDisplayed(){
        return webUtils.isElementDisplayed(driver, descriptionFieldError);
   }

   public boolean isAssignedToErrorDisplayed(){
        return webUtils.isElementDisplayed(driver, assignedToFieldError);
   }

    public boolean isCreatedByrDisplayed(){
        return webUtils.isElementDisplayed(driver, createdByFieldError);
    }

    public void clickOnIncidentBtn(){
        webUtils.clickAction(driver, incidentBtn);
    }

    public void enterTitle(String title){
        webUtils.sendKeysAction(driver,titleField, title);
    }
    public void enterDescription(String description){
    webUtils.sendKeysAction(driver, descriptionField, description);
    }
    public void selectAssignedToOption(String option){
    webUtils.selectDDbyText(driver, assignedToField, option);
    }
    public void enterCreatedByField(String createdBy){
    webUtils.sendKeysAction(driver, createdByField, createdBy);
    }
    public void selectPriorityOption(String option){
        webUtils.selectDDbyText(driver, priorityField, option);
    }
    public void selectStatusOption(String option){
        webUtils.selectDDbyText(driver, statusField, option);

    }

    public void clickOnSaveIncidentButton(){
        webUtils.clickAction(driver, saveIncidentBtn);
    }


}

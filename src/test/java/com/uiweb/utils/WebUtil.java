package com.uiweb.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebUtil {

    final static Logger logger = Logger.getLogger(WebUtil.class);

    /**
     * Method to check if element is present
     * */
    public void checkElement(WebDriver driver, By locator){

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    /**
     * Method to check if all the elements are present
     * */
    public void checkElements(WebDriver driver, By locator){

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Method to find a single element
     * @param locator
     * @return element
     * */
    public WebElement element(WebDriver driver, By locator){
        checkElement(driver, locator);
        WebElement element = null;
        try{
            element = driver.findElement(locator);
            logger.info("Element found by "+locator);
        }catch(Exception e){
            logger.info("Error in the method element "+e);
        }

        return element;
    }


    /**
     * Method to find list of elements
     * @param locator
     * @return elements
     * */
    public List<WebElement> elements(WebDriver driver, By locator){
        checkElements(driver, locator);
        List<WebElement> elements = null;
        try{
            elements = driver.findElements(locator);
            logger.info("Elements found by "+locator);
        }catch(Exception e){
            logger.info("Error in the method elements "+e);
        }

        return elements;
    }

    /**
     * Method to get element by using getText()
     * @param locator
     * @return
     */
    public String elementGetText(WebDriver driver,By locator) {
        checkElement(driver,locator);
        WebElement element = null;

        try {
            element = driver.findElement(locator);
            logger.info("Element Get Text performed on element "+locator);
        } catch (Exception e) {
            logger.info("Error in method elementGetText "+e);
        }
        return element.getText();
    }

    /**
     * Method to get element by using getAttribute()
     * @param locator
     * @return String
     */
    public String elementGetValue(WebDriver driver,By locator) {
        checkElement(driver,locator);
        WebElement element = null;

        try {
            element = driver.findElement(locator);
            logger.info("Element Get Value performed on element "+locator);
        } catch (Exception e) {
            logger.info("Error in method elementsGetValue "+e);
        }
        return element.getAttribute("value");
    }

    /**
     * Method to get list of elements text
     * @param locator
     * @return list of text
     */
    public ArrayList<String> elementsGetText(WebDriver driver, By locator) {
        checkElements(driver,locator);
        List<WebElement> elements = null;
        ArrayList<String> text = new ArrayList<String>();

        try {
            elements = driver.findElements(locator);
            elements.forEach(webElement -> {
                String textValue = webElement.getText();
                text.add(textValue);

            });
            logger.info("ElementsGetText performed on element "+locator);
        } catch (Exception e) {
            logger.info("Error in method elementsGetText "+e);
        }
        return text;
    }

    /**
     * Method to use sendKeys actions
     * @param locator, value
     * @return none
     */
    public void sendKeysAction(WebDriver driver,By locator, String value) {
        checkElement(driver,locator);
        WebElement element = null;

        try {
            element = driver.findElement(locator);
            element.sendKeys(value);
            logger.info("Entered value "+value+" in element "+locator);
        } catch (Exception e) {
            logger.info("Error in method sendKeyAction "+e);
        }
    }

    /**
     * Method to click on an element
     * @param locator
     * @return none
     */
    public void clickAction(WebDriver driver, By locator) {
        checkElement(driver,locator);
        WebElement element = null;
        try {

            element = driver.findElement(locator);
            element.click();
            logger.info("Clicked on element "+locator);
        } catch (Exception e) {
            logger.info("Error in method clickAction "+e);

        }
    }

    /**
     * Method to determine if element is displayed
     * @param locator
     * @return boolean
     */
    public boolean isElementDisplayed(WebDriver driver,By locator) {

        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(d -> driver.findElement(locator).isDisplayed());
        } catch (Exception e) {
            logger.info("Error in method isElementDisplayed "+e);
        }
        return driver.findElement(locator).isDisplayed();

    }

    /**
     * Method to select drop down by text
     * @param locator
     * @return boolean
     */
    public void selectDDbyText(WebDriver driver,By locator, String visibleText) {
        Select select = new Select(element(driver,locator));
        try {
            select.selectByVisibleText(visibleText);
        } catch (Exception e) {
            logger.info("Error in method selectDDbyText "+e);
        }

    }

    /**
     * @param locator
     * @param value
     */
    public void selectDDbyValue(WebDriver driver,By locator, String value) {
        Select select = new Select(element(driver,locator));
        try {
            select.selectByValue(value);
        } catch (Exception e) {
            logger.info("Error in method selectDDbyValue "+e);
        }

    }

    /**
     * @param locator
     * @param
     */
    public void sendKeyboardAction(WebDriver driver,By locator, Keys value) {
        checkElement(driver,locator);
        WebElement element = null;

        try {
            element = driver.findElement(locator);
            element.sendKeys(value);
        } catch (Exception e) {
            logger.info("Error in method sendKeyboardAction "+e);
        }
    }







}

package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {

	WebDriver driver;
	
	public ElementUtil(WebDriver driver){ //Constructor..
		this.driver=driver;
	}
	
	
	/**
	 * This method is used to create the WebElement on the basis of By Locator.
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator){
		waitForElementPresent(locator, 20);
		WebElement element = null;
		try{
		element = driver.findElement(locator);
//		if(prop.getProperty("elementflash").equalsIgnoreCase("yes")) {
//			JavaScriptUtil.flash(element, driver);
//		}
		} 
		catch(Exception e){
			System.out.println("some exception occured while creating webelement.." +locator);
			System.out.println(e.getMessage());
		}
		return element;
		}
	
	/**
	 * This method is used to wait for an element on basis of locator.
	 * @param locator
	 * @param timeOut
	 */
	public void waitForElementPresent(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This method is used to wait for title using Explicit
	 * @param title
	 * @param timeOut
	 */
	public String waitForPageTitle(String title, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	
	/**
	 * This method is used to click on the Element on basis of By Locator.
	 * @param locator
	 */
	public void doClick(By locator){
		try{
		getElement(locator).click();
		}
		catch(Exception e){
			System.out.println("some exception occured while clicking on web element" +locator);
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * This method is used to click on the Element on basis of By Locator.
	 * @param locator
	 */
	public void doActionsClick(By locator){
		try{
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
//		getElement(locator).click();
		}
		catch(Exception e){
			System.out.println("some exception occured while clicking on the webelement");
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * This method is used to pass the values in a WebElement. 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value){
		try{
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		}
		catch(Exception e){
			System.out.println("some exception occured while sending to webelement");
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * This method is used to pass the values in a WebElement on basis of Action class. 
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value){
		try{
			Actions action = new Actions(driver);
			action.sendKeys(getElement(locator), value).build().perform();
			//getElement(locator).sendKeys(value);
		}
		catch(Exception e){
			System.out.println("some exception occured while passing values to the web element");
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * This method is used to get the text of the Element on basis of By Locator.
	 * @param locator
	 */
	public String doGetText(By locator){
		String text = null;
		try{
		text =  getElement(locator).getText();
		}
		catch(Exception e){
			System.out.println("some exception occured while getting text of the web element");
			System.out.println(e.getMessage());
		}
		return text;
	}
		
	
	/**
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isElementDisplayed(By locator){
		try{
		return getElement(locator).isDisplayed();
		}
		catch(Exception e){
			System.out.println("some exception occured while checking the element is displayed " +locator);
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	/**
	 * This method is used to click on the Webelement by locator on basis of Action class.
	 * @param driver
	 * @param parentMenuLocator
	 * @param subMenuLocator
	 * @throws InterruptedException
	 */
	public static void clicOnSubMenuLink(WebDriver driver, By parentMenuLocator,By subMenuLocator){
		Actions action = new Actions(driver);
		WebElement parentMenuElement = driver.findElement(parentMenuLocator);
		action.moveToElement(parentMenuElement).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
		WebElement childElement = driver.findElement(subMenuLocator);	
		childElement.click();
		
		
	}
	
	/******These two methods are called in BasePage Class ******/
	/**
	 * This method is used to quit the Browser..
	 * @param driver
	 */
//	public void quitBrowser(){
//		try {
//			driver.quit();
//		}
//		catch(Exception e) {
//			System.out.println("Exception occured while quitting the browser");
//		}
//	}
	

//	/**
//	 * this method is used to close the browser
//	 */
//	public void closeBrowser() {
//		try {
//			driver.close();
//		}
//		catch(Exception e) {
//			System.out.println("Exception occured while closing the browser");
//		}
	}

	
	
	

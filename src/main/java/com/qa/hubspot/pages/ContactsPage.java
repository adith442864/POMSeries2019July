package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

/**
 * 
 * @author Adith
 *
 */

public class ContactsPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;

	By createContactButton = By.xpath("//span[text()='Create contact']");
	By createContactFormButton = By.xpath("//button[@data-selenium-test='base-dialog-confirm-btn']");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");

	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getContactsPageTitle() {
		return elementUtil.waitForPageTitle(Constants.CONTACTS_PAGE_TITLE, 20);
	}

	public void createNewContact(String emailID, String FN, String LN, String jobTitleVal) {
		
		elementUtil.waitForElementPresent(createContactButton, 15);
		elementUtil.doClick(createContactButton);
		
		elementUtil.waitForElementPresent(email, 10);
		elementUtil.doSendKeys(email, emailID);
		
		elementUtil.waitForElementPresent(firstName, 10);
		elementUtil.doSendKeys(firstName, FN);
		
		elementUtil.waitForElementPresent(lastName, 10);
		elementUtil.doSendKeys(lastName, LN);
		
		elementUtil.waitForElementPresent(jobTitle, 5);
		elementUtil.doSendKeys(jobTitle, jobTitleVal);
		
		elementUtil.waitForElementPresent(createContactFormButton, 15);
		elementUtil.doClick(createContactFormButton);
	}



}

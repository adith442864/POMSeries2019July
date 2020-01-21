package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

/**
 * 
 * @author Adith Balaji
 *
 */

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;	
	
	By header = By.xpath("//h1[@class='private-page__title']");
	By accountName = By.xpath("//a[@id='account-menu']//span[contains(text(), 'test')]");
	
	By contactsMainTab = By.id("nav-primary-contacts-branch");
	By conatctsChildTab = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		return elementUtil.waitForPageTitle(Constants.HOME_PAGE_TITLE, 20);
	}
	
	public String getHomePageHeaderValue() {
		return elementUtil.doGetText(header);
	}
	
	public boolean verifyLoggedInAccountName() {
		return elementUtil.isElementDisplayed(accountName);	
	}
	
	public String getLoggedInAccountName() {
		return elementUtil.doGetText(accountName);
	}
	
	private void clickOnContacts(){
		elementUtil.doClick(contactsMainTab);
		elementUtil.doClick(conatctsChildTab);
	}
	
	public ContactsPage gotoContactsPage(){
		clickOnContacts();
		return new ContactsPage(driver);
	}

}

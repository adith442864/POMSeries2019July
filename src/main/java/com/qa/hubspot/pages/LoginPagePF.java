package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.util.ElementUtilPF;

public class LoginPagePF {
	
	WebDriver driver;
	ElementUtilPF elementUtilPF;
	
	//PageFactory WebElement
	@FindBy(id="username")
	WebElement emailId;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="loginBtn")
	WebElement loginButton;
	
	public LoginPagePF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		elementUtilPF = new ElementUtilPF(driver);
	}
	
	
	public void doLogin(String username, String pwd) {
		elementUtilPF.waitForElementPresent(emailId); //explicit wait...
		emailId.sendKeys(username);
		password.sendKeys(pwd);
		loginButton.click();
	}
	
	
	

}

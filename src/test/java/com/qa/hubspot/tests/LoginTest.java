package com.qa.hubspot.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.ElementUtil;

public class LoginTest {
	
	Properties prop;
	WebDriver driver;
	BasePage basePage;	
	LoginPage loginPage;
	
	
	@BeforeMethod
	public void SetUp() {
	basePage = new BasePage();
	prop = basePage.initialize_properties();
	String browser = prop.getProperty("browser");
	driver = basePage.initialize_driver(browser);
	loginPage = new LoginPage(driver);
	
	}
	

	@Test(priority=1)
	public void verifyloginPageTitleTest() {
		String title = loginPage.verifyLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, "HubSpot Login");
	}
	
	@Test(priority=2)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		basePage.quitBrowser();
	}

}

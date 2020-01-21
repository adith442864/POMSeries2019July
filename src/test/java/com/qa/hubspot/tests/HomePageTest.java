package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class HomePageTest {

	Properties prop;
	WebDriver driver;
	BasePage basePage;	
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void SetUp() {
	basePage = new BasePage();
	prop = basePage.initialize_properties();
	String browser = prop.getProperty("browser");
	driver = basePage.initialize_driver(browser);
	loginPage = new LoginPage(driver);
	homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); //chain between loginpage and homepage
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
//	homePage = new HomePage(driver);
	
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is: "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeaderValue();
		System.out.println("header value is :" + header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserAccountTest() {
		Assert.assertTrue(homePage.verifyLoggedInAccountName());
//		String accountName = homePage.getLoggedInAccountName();	
//		System.out.println("Logged In Account Name is " +accountName);
//		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}

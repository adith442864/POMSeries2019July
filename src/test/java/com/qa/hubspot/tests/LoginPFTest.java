package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.pages.LoginPagePF;

public class LoginPFTest {
	Properties prop;
	WebDriver driver;
	BasePage basePage;	
	LoginPagePF loginPagePF;
	
	
	@BeforeMethod
	public void SetUp() {
	basePage = new BasePage();
	prop = basePage.initialize_properties();
	String browser = prop.getProperty("browser");
	driver = basePage.initialize_driver(browser);
	loginPagePF = new LoginPagePF(driver);
	
	}
	
	@Test
	public void loginTest() {
		loginPagePF.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterMethod
	public void tearDown() {
		basePage.quitBrowser();
	}

	
	

}

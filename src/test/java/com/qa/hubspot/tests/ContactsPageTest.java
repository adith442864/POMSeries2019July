package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ExcelUtil;

public class ContactsPageTest {

	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeTest
	public void SetUp() {
	basePage = new BasePage();
	prop = basePage.initialize_properties();
	String browser = prop.getProperty("browser");
	driver = basePage.initialize_driver(browser);
	loginPage = new LoginPage(driver);
	homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); //chain between loginpage and homepage
	contactsPage = homePage.gotoContactsPage();

//	homePage = new HomePage(driver);
	
	}

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("contacts page title is: " + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}
	
	
	@DataProvider()
	public Object[][] getContactData(){
		Object data[][] = ExcelUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return data;
	}

	@Test(priority = 2, dataProvider="getContactData")
	public void createNewContactTest(String email, String firstname, String lastname, String jobTitle) {
		contactsPage.createNewContact(email, firstname, lastname, jobTitle);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	
	
}

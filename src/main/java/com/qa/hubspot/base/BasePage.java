package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	
public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public Properties initialize_properties() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return prop;
	}
	
	
	public WebDriver initialize_driver(String browser) {
		String headless = prop.getProperty("headless");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
				if(headless.equalsIgnoreCase("yes")) {
					ChromeOptions co = new ChromeOptions();
					co.addArguments("--headless");
					driver = new ChromeDriver(co);
				} else {
					driver = new ChromeDriver();
				}
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	public void quitBrowser(){
		try {
			driver.quit();
		}
		catch(Exception e) {
			System.out.println("Exception occured while quitting the browser");
		}
	}
	

	/**
	 * this method is used to close the browser
	 */
	public void closeBrowser() {
		try {
			driver.close();
		}
		catch(Exception e) {
			System.out.println("Exception occured while closing the browser");
		}
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

		
}

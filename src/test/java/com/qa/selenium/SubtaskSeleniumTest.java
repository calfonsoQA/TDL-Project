package com.qa.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SubtaskSeleniumTest {

	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "file:///C:/Users/admin/Documents/workspace-spring-tool-suite-4-4.9.0.RELEASE/TDL-Project/src/main/resources/static/index.html";
	
	// Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
	public static ChromeOptions chromeCfg() {
	 Map<String, Object> prefs = new HashMap<String, Object>();
	 ChromeOptions cOptions = new ChromeOptions();
	  
	 // Settings
	 prefs.put("profile.default_content_setting_values.cookies", 2);
	 prefs.put("network.cookie.cookieBehavior", 2);
	 prefs.put("profile.block_third_party_cookies", true);

	 // Create ChromeOptions to disable Cookies pop-up
	 cOptions.setExperimentalOption("prefs", prefs);

	 return cOptions;
	 }

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
	}

	@AfterAll
	public static void afterAll() {
		driver.quit();
	}
	
	

	@Test
	public void subtasksDisplay() throws InterruptedException {
		
//		GIVEN:
		driver.get(URL);
		
//		WHEN: I search Kittens using the search bar
		targ = driver.findElement(By.name("q"));
		targ.sendKeys("Kittens");
		targ.submit();
		
//		AND: I select images
		
		
//		THEN: I should get results of kitten images
		String result = driver.getTitle();
		
//		And: I can select the 5th
		
//		Assertions
		assertEquals("Kittens - Google Search", result);
//		Thread.sleep(10000);
	}
}

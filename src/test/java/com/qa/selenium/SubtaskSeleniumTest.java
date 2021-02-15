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
	private final String URL = "http://localhost:8080/index.html";
	
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
		
//		WHEN: I first enter and see the web page
		targ = driver.findElement(By.xpath("//*[@id=\"subtaskLists\"]/div[3]/div/div[3]"));
		
//		AND: I select images
		
		
//		THEN: I should see the first task and subtask result
		String result = targ.getText();
		
//		And: I can select the 5th
		
//		Assertions
		assertEquals("Buy utensils", result);
//		Thread.sleep(10000);
	}
}

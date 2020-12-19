package com.givelify.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.givelify.pages.ForgotPasswordWindow;
import com.givelify.pages.LandingPage;
import com.givelify.pages.SigninWindow;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public static WebDriver driver;
//	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriverWait wait;
	public static String browser;
	// three page clases are declared here
	public LandingPage landingpageobj;
	public SigninWindow signinWindowobj;
	public ForgotPasswordWindow forgotemailwinobj;

	@BeforeTest
	public void openBrowser() {
		if (Constants.browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver(options);
			// log.debug("Launching Chrome");
		} else if (Constants.browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			// log.debug("Launching IE");
		}
		driver.get(Constants.testsiteurl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// two page clases are instantiated here with a parameterized Constructor
		// passing the Webdriver reference
		landingpageobj = new LandingPage(driver);
		signinWindowobj = new SigninWindow(driver);
		forgotemailwinobj = new ForgotPasswordWindow(driver);
	}

	public static void click(WebElement element) {
		element.click();
		/*
		 * log.debug("Clicking on an Element : " + element); test.log(LogStatus.INFO,
		 * "Clicking on : " + element);
		 */
	}

	public static void type(WebElement element, String value) {
		element.sendKeys(value);
		/*
		 * log.debug("Typing in an Element : " + element + " entered value as : " +
		 * value); test.log(LogStatus.INFO, "Typing in : " + element +
		 * " entered value as " + value);
		 */
	}
	/*
	 * @AfterSuite public void quitBrowser() { driver.quit(); }
	 */
}

package com.givelify.base;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	public WebDriver driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void prerequisuiteForSignIn() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver.get(Constants.testsiteurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement signin = driver.findElement(By.xpath("//span[contains(text(),'Sign In')]"));
		signin.click();
	}

	@Test(priority = 1)
	public void ValidsingInTest() throws InterruptedException {
		String validUserEmail = "debora11.2k@gmail.com";
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(validUserEmail);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Remarkable@2020");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In') and @color='skyblue']")).click();
		Thread.sleep(5000);
		WebElement alertpromtdonations = driver.findElement(By.xpath("//div[contains(text(),' sends donation')]"));
		String donationstext = alertpromtdonations.getText();
		String expecteddonationtest = "Givelify sends donation receipts and statements to your email. To ensure we have the correct address, check "
				+ validUserEmail + " for a Givelify verification email and click on the link in there.";
		Assert.assertEquals(donationstext, expecteddonationtest);
	}

	@Test(priority = 2) // invalid senario with unregistered email
	public void UnRegisteredEmailSignInTest() throws InterruptedException {
		String validUserEmail = "deborah7711.2k@gmail.com";
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(validUserEmail);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Test@2020");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In') and @color='skyblue']")).click();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[contains(text(),'Check the email address/password ')]")));
		String donationstext = driver
				.findElement(By.xpath("//div[@class='sc-jtRfpW bVoJNj alert alert-danger fade show']")).getText();
		String expecteddonationtest = "Check the email address/password and try again. If you don't have an account to give on Givelify, you can sign up by pressing Join Givelify";
		Assert.assertEquals(donationstext, expecteddonationtest);
	}

	@Test(priority = 3)
	// invalid senario with not an email entered
	public void InValidEmailsingInTest() throws InterruptedException {
		WebElement alertnotvalidemail = driver.findElement(By.xpath("//input[@type='email']"));
		alertnotvalidemail.sendKeys("Ananya123%%%");
		alertnotvalidemail.sendKeys(Keys.TAB);

				wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='sc-jAaTju gfVFPZ']")));

		String actualalertEmail = driver.findElement(By.xpath("//span[contains(text(),'This field must')]")).getText();
		String expectedalertEmail = "This field must be an email";
		Assert.assertEquals(actualalertEmail, expectedalertEmail);

	}

	@Test(priority = 4) // invalid senario with blank fields
	public void singInButtonStatus() throws InterruptedException {
		WebElement alertpromtdonations = driver
				.findElement(By.xpath("//button[@color='disable' and @class='sc-EHOje gECnpk']"));

		Assert.assertFalse(alertpromtdonations.isEnabled(),
				"Sign In button is disabled as no email and no password is present");
	}

	@Test(priority = 5)
	public void validateForgotPwd() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'Forgot password?')]")).click();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//input[@placeholder='Email' and @type='email']"))));
		driver.findElement(By.xpath("//input[@placeholder='Email' and @type='email']"))
				.sendKeys("debora11.2k@gmail.com");
		driver.findElement(By.xpath("//button[contains(text(),'Reset Password')]")).click();
		Thread.sleep(2000);
		assertTrue(driver
				.findElement(
						By.xpath("//div[@class='sc-jtRfpW bVoJNj alert alert-success alert-dismissible fade show']"))
				.isDisplayed());
	}

	@Test(priority = 6)
	public void validateForgotPwdinvalidEmail() {
		driver.findElement(By.xpath("//span[contains(text(),'Forgot password?')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Email' and @type='email']")).sendKeys("deb@gmail.com");
		driver.findElement(By.xpath("//button[contains(text(),'Reset Password')]")).click();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//span[@class='sc-jAaTju gfVFPZ' and contains(text(),'We could not')]")));
		String actualText = driver
				.findElement(By.xpath("//span[@class='sc-jAaTju gfVFPZ' and contains(text(),'We could not')]"))
				.getText();
		assertEquals(actualText,
				"We could not find your email address. Can you please check your email address and try again.");
	}
	@Test(priority = 7)
	public void validateForgotPwdinvalidtext() throws InterruptedException {
		driver.findElement(By.xpath("//span[contains(text(),'Forgot password?')]")).click();
		WebElement forgotEmail = driver.findElement(By.xpath("//input[@placeholder='Email' and @type='email']"));
		forgotEmail.sendKeys("!!!!!!!!");
		forgotEmail.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//span[@class='sc-jAaTju gfVFPZ' and contains(text(),'This field')]")));
		String actualText = driver
				.findElement(By.xpath("//span[@class='sc-jAaTju gfVFPZ' and contains(text(),'This field')]"))
				.getText();
		
		assertEquals(actualText,
				"This field must be an email");
	}

	@AfterMethod
	public void tearDown() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}

package com.givelify.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.givelify.base.BaseClass;
import com.givelify.pages.SigninWindow;

public class SignInTest extends BaseClass {

	@Test(priority = 1)
	public void ValidSignInTest() throws InterruptedException {
		landingpageobj.clickOnSIgnIn();
		String validUserEmail = "debora11.2k@gmail.com";
		// send the email and password as parameters to the method in Page Class
		SinginWindowobj.doLogin("debora11.2k@gmail.com", "Remarkable@2020");
		// get the alert for donations and vlaidate if correct message
		SinginWindowobj.validateSucessDonationMsg(validUserEmail);
	}

	@Test(priority = 2)
	public void UnRegisteredEmailSignInTest() throws InterruptedException {
		driver.navigate().back();
		driver.navigate().forward();
		SinginWindowobj.doLogin("anu1@gmail.com", "Remarkable@2021");
		SinginWindowobj.validatenotregisteredEmailSignINalert();
	}

	@Test(priority = 3)
	// invalid senario with not an email entered
	public void InValidEmailsingInTest() throws InterruptedException {
		driver.navigate().back();
		driver.navigate().forward();
		// to get a random number between 0 and 1, then mulitple by some number
		double a = (Math.random() * 500);
		// when we concatinate a number with a string --> gets converted to a string
		String s = "!!!!!!!!!!!!!!!!!!!" + a + "!!!!!!!!!!!!!!!!!";
		SinginWindowobj.doLogin(s, "Remarkable@2021");
		SinginWindowobj.validateNotanEmail();
	}

	@Test(priority = 4) // invalid senario with blank fields
	public void singInButtonStatus() throws InterruptedException {
		driver.navigate().back();
		driver.navigate().forward();
		SinginWindowobj.verifysingInButnStatus();
		
	}

}

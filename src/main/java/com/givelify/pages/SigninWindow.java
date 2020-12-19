package com.givelify.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.givelify.base.BaseClass;

public class SigninWindow extends BaseClass {

	// email
	@FindBy(xpath = "//input[@type='email']")
	private WebElement email;

	// passoword
	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	// forgotpassword
	@FindBy(xpath = "//span[contains(text(),'Forgot password?')]")
	private WebElement forgotpassword;

	// notregisteredEmailSignINalert
	@FindBy(xpath = "//div[contains(text(),'Check the email address/password ')]")
	private WebElement notregisteredEmailSignINalert;

	// invalidEmailSignINalert
	@FindBy(xpath = "//span[contains(text(),'This field must')]")
	private WebElement invalidEmailSignINalert;

	// alertpromtdonations
	@FindBy(xpath = "//div[contains(text(),' sends donation')]")
	private WebElement alertpromtdonations;

	// signInWhenEnabled
	@FindBy(xpath = "//button[contains(text(),'Sign In') and @color='skyblue']")
	private WebElement submit;

	// submitwhenDisabled
	@FindBy(xpath = "//button[@color='disable' and @class='sc-EHOje gECnpk']")
	private WebElement submitwhenDisabled;

	public SigninWindow(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void doLogin(String uname, String pwd) throws InterruptedException {
		type(email, uname);
		type(password, pwd);
		click(submit);
	}

	public void validateSucessDonationMsg(String validUserEmail) {
		String donationstext = alertpromtdonations.getText();
		String expecteddonationtest = "Givelify sends donation receipts and statements to your email. To ensure we have the correct address, check "
				+ validUserEmail + " for a Givelify verification email and click on the link in there.";
		Assert.assertEquals(donationstext, expecteddonationtest);
	}
	public void validatenotregisteredEmailSignINalert() {
		String donationstext = notregisteredEmailSignINalert.getText();
		String expecteddonationtest = "Check the email address/password and try again. If you don't have an account to give on Givelify, you can sign up by pressing Join Givelify";
		Assert.assertEquals(donationstext, expecteddonationtest);
	}
	public void validateNotanEmail() {
		String actualalertEmail = invalidEmailSignINalert.getText();
		String expectedalertEmail = "This field must be an email";
		Assert.assertEquals(actualalertEmail, expectedalertEmail);
	}

	public void verifysingInButnStatus() {
		Assert.assertFalse(submitwhenDisabled.isEnabled(),
				"Sign In button is disabled as no email and no password is present");
		
	}



}

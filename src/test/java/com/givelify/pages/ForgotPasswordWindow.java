package com.givelify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.givelify.base.BaseClass;

public class ForgotPasswordWindow {
	// forgotemailtextbox
	@FindBy(xpath = "//input[@placeholder='Email' and @type='email']")
	private WebElement forgotEmailIDtextbox;
	// forgotemailsubmit
	@FindBy(xpath = "//button[contains(text(),'Reset Password')]")
	private WebElement forgotemailsubmit;
	// resetsuccessmessage
	@FindBy(xpath = "//span[contains(text(),'We will send you')]")
	private WebElement resetsuccessmessage;
	// resetErrorInvalidEmailmessage
	@FindBy(xpath = "//span[contains(text(),'Enter the Email address you used to sign up')]")
	private WebElement resetErrorInvalidEmailmessage;
	// forgotemailcancel
	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement forgotemailcancel;

	// constructor
	public ForgotPasswordWindow(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickOnForgotSubmit(String forgotemail) {
		forgotEmailIDtextbox.sendKeys(forgotemail);
		forgotemailsubmit.click();
	}
	public void validateTextWithCorrectEmail() {
		Assert.assertEquals(resetsuccessmessage.getText(), "We will send you instruction to reset your password");
	}
	public void clickOnCancel() {
		forgotemailcancel.click();
	}
	public void validateTextWithInCorrectEmail() {
		Assert.assertEquals(resetErrorInvalidEmailmessage.getText(), "Enter the Email address you used to sign up");
	}
}

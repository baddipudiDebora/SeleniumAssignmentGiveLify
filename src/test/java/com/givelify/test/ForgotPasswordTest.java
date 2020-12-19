package com.givelify.test;

import org.testng.annotations.Test;

import com.givelify.base.BaseClass;

public class ForgotPasswordTest extends BaseClass {
	@Test(priority = 1)
	public void validEmailForgotTest() throws InterruptedException {
		landingpageobj.clickOnSIgnIn();
		signinWindowobj.clickOnForgotPassword();
		forgotemailwinobj.clickOnForgotSubmit("swaroop.22k@gmail.com");
		forgotemailwinobj.validateTextWithCorrectEmail();
	}
	@Test(priority = 2) 
	public void InValidEmailForgotTest() throws InterruptedException {
		forgotemailwinobj.clickOnCancel();
		signinWindowobj.clickOnForgotPassword();
		forgotemailwinobj.clickOnForgotSubmit("sara12345@gmail.com");
		forgotemailwinobj.validateTextWithInCorrectEmail();
	}
}

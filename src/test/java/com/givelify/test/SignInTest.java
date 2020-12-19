package com.givelify.test;

import org.testng.annotations.Test;

import com.givelify.base.BaseClass;
import com.givelify.base.Constants;

public class SignInTest extends BaseClass {

	@Test(priority = 1)
	public void ValidSignInTest() throws InterruptedException {
		landingpageobj.clickOnSIgnIn();
		// send the email and password as parameters to the method in Page Class
		signinWindowobj.doLogin("swaroop.22k@gmail.com", "Remarkable@2020");
		// get the alert for donations and validate if correct message
		signinWindowobj.clickContinue();
		signinWindowobj.clickDownArrowandLogout();
	
	}
	@Test(priority = 2 )
	public void InValidPasswordTest() throws InterruptedException {
		landingpageobj.clickOnSIgnIn();
		// send the email and password as parameters to the method in Page Class
		signinWindowobj.doLogin("swaroop.22k@gmail.com", "arkable@2020");
		// check for the error message
		signinWindowobj.validateIncorrectPwdNalert();
	
	}

	@Test(priority = 3)
	public void UnRegisteredEmailSignInTest() throws InterruptedException {
		driver.navigate().back();
		driver.navigate().forward();
		signinWindowobj.doLogin("anu1@gmail.com", "Remarkable@2021");
		// check for the error message
		signinWindowobj.validatenotregisteredEmailSignINalert();
	}
	

	@Test(priority = 4) // invalid senario with blank fields
	public void singInButtonStatus() throws InterruptedException {
		driver.navigate().refresh();
		signinWindowobj.verifysingInButnStatus();

	}

}

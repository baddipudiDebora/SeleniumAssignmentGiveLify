package com.givelify.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.givelify.base.BaseClass;

public class LandingPage extends BaseClass {
	@FindBy(xpath = "//span[contains(text(),'Sign In')]")
	private WebElement singIN;

	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public void clickOnSIgnIn() {
		singIN.click();
	}

}

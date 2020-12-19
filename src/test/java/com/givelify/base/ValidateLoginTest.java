package com.givelify.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ValidateLoginTest extends BaseClass {
public static void main(String[] args) {
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	WebElement signin = driver.findElement(By.xpath("//span[contains(text(),'Sign In')]"));
	signin.click();
}
}

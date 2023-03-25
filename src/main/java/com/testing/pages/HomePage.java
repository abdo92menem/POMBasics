package com.testing.pages;

import org.openqa.selenium.By;

import com.testing.base.Page;

public class HomePage extends Page {
		
	public void goToSupport(){
		
		driver.findElement(By.cssSelector("a.zh-login")).click();
		
	}
	
	public void goToSignUp(){
		
		driver.findElement(By.cssSelector(".signup")).click();
	}
	
	
	public LoginPage goToLogin(){
		
		click("loginlink_CSS");
		
		return new LoginPage();
	}
	
	public void goToZohoEdu(){
		
		
		
	}
	
	public void goToLearnMore(){
		
		
		
	}
	
	
	public void validateFooterLinks(){
		
		
	}
}

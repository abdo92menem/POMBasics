package com.testing.pages;

import org.openqa.selenium.By;

import com.testing.base.Page;
import com.testing.pages.crm.CRMHomePage;

public class ZohoAppPage  extends Page{
	
	public void gotoEmail(){
		
		driver.findElement(By.cssSelector("#zl-category-1 > div > ul > li:nth-child(2) > div > a")).click();
		
	}
	
	public CRMHomePage gotoCRM(){
		
		click("crmLink_CSS");
		
		return new CRMHomePage();
	}
	
	
	public void gotoSalesIQ(){
		
		driver.findElement(By.cssSelector(".zicon-apps-salesiq.zicon-apps-96")).click();
		
	}

}

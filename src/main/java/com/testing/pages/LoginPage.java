package com.testing.pages;

import com.testing.base.Page;

public class LoginPage extends Page {
	
	public ZohoAppPage doLogin(String username,String password){
		
		type("email_CSS", username);
		click("nextBtn_CSS");
		type("password_CSS", password);
		click("signBtn_CSS");
		
		return new ZohoAppPage();
	}
	
	
	public void gotoSalesandMarketing(){
		
		
	}
	
	public void gotoFinance(){
		
		
	}
	
}

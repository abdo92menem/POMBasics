package com.testing.pages.crm.accounts;

import com.testing.base.Page;

public class CreateAccountPage  extends Page {
	
	
	public void createAccount(String accountName){
		
		type("accountName_CSS",accountName);
		
	}

}

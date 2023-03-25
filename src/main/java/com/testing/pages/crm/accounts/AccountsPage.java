package com.testing.pages.crm.accounts;

import com.testing.base.Page;

public class AccountsPage  extends Page{
	
	public CreateAccountPage gotoCreateAccounts(){
		
		click("createAccountBtn_CSS");
		
		return new CreateAccountPage();
	}
	
	
	public void gotoImportAccounts(){
		
		
	}

}

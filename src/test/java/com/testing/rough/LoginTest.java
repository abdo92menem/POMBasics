package com.testing.rough;

import com.testing.base.Page;
import com.testing.pages.HomePage;
import com.testing.pages.LoginPage;
import com.testing.pages.ZohoAppPage;
import com.testing.pages.crm.accounts.AccountsPage;
import com.testing.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	public static void main(String[] args) {

		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zp = lp.doLogin("abdullah.abdelmenem@gmail.com", "Abdo92@@##");
		zp.gotoCRM();
		AccountsPage account = Page.menu.gotoAccounts();
		CreateAccountPage cap = account.gotoCreateAccounts();
		cap.createAccount("Raman");
		
	}

}

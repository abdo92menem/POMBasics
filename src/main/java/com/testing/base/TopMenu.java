package com.testing.base;

import org.openqa.selenium.WebDriver;

import com.testing.pages.crm.accounts.AccountsPage;

public class TopMenu{

	/*
	 * 
	 * TopMenu ISA Page ???
	 * 
	 * HomePage HASA TopMenu
	 * AccountsPAge HASA TopMenu
	 * 
	 * Page HASA TopMenu
	 * 
	 * 
	 */
	
	WebDriver driver;
	
	public TopMenu(WebDriver driver){
		
		this.driver = driver;
	}
	
	public void gotoHome() {

	}

	public void gotoFeeds() {

	}

	public void gotoLeads() {

	}

	public AccountsPage gotoAccounts() {
		
		
		Page.click("accountsTab_CSS");
		
		return new AccountsPage();
	}

	public void gotoContacts() {

	}
	
	
	public void signOut(){
		
		
	}

}

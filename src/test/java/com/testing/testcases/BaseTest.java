package com.testing.testcases;

import org.testng.annotations.AfterSuite;

import com.testing.base.Page;

public class BaseTest {
	
	
	@AfterSuite
	public void tearDown(){
		
		Page.tearDown();
		
	}

}

package com.testing.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentManager {
	
	private static ExtentReports extent;
	
	
	public static ExtentReports createInstance(String fileName) {
		
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Abdullah Abdel Menem");
		extent.setSystemInfo("Organization", "TestingAcademy");
		extent.setSystemInfo("Build No.", "TA-1234");
		
		return extent;
	}

}

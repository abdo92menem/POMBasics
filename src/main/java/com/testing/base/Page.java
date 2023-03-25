package com.testing.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testing.utilities.ExcelUtility;
import com.testing.utilities.Utilities;

public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger("devpinoyLogger");
	public static ExcelUtility excel = new ExcelUtility("./src/test/resources/com/testing/excel/testdata.xlsx");
	public static WebDriverWait wait;
	public static Alert alert;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static String browser;

	public static TopMenu menu;

	/*
	 * Logs,
	 * Properties - OR, Config
	 * Excel
	 * Implicit and ExplicitWait
	 * Extent Reports
	 * 
	 * 
	 * 
	 * 
	 */

	public Page() {

		if (driver == null) {

			try {
				fis = new FileInputStream("./src/test/resources/com/testing/properties/Config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {

				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("./src/test/resources/com/testing/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Jenkins Browser filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);


			if (config.getProperty("browser").equals("firefox")) {

				driver = new FirefoxDriver();
				log.debug("Firefox Launched !!!");

			} else if (config.getProperty("browser").equals("chrome")) {

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				

				driver = new ChromeDriver(options);

				log.debug("Chrome Launched !!!");

			}else if (config.getProperty("browser").equals("edge")) {

				System.out.println("Edge is launched");
				driver = new EdgeDriver();
				log.debug("Edge Launched !!!");

			}
			driver.get(config.getProperty("testSiteURL"));
			log.debug("Navigating to : " + config.getProperty("testSiteURL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicitWait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			menu = new TopMenu(driver);
		}
	}



	public static void tearDown(){

		if (driver != null) {

			driver.quit();

		}

		log.debug("Test Execution completed !!!");

	}


	//Common Keywords
	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			
		} else if (locator.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		} else if (locator.endsWith("_ID")) {
			
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		
		locator = locator.split("_")[0];
		
		log.debug("Clicking on an Element : " + locator);
		testReport.get().log(Status.INFO, "Clicking on : " + locator);
	}

	public static void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		
		locator = locator.split("_")[0];

		log.debug("Typing in an Element : "+locator+" entered value as : "+value);
		testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + value);

	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		locator = locator.split("_")[0];
		
		log.debug("Selecting from an element : "+locator+" value as : "+value);
		testReport.get().log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {
		
			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			Utilities.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName)
							.build());
			
			String failureLogg = "Verification Failed with Exception: " + t.getMessage();
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			testReport.get().log(Status.FAIL, m);

		}

	}
}

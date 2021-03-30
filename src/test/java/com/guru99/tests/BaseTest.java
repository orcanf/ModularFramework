package com.guru99.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.guru99.pages.LoginPage;
import com.guru99.utils.ConfigUtils;
import com.guru99.utils.ReportUtils;
import com.guru99.utils.ScreenshotUtils;

import commonLibs.implementation.CommonDriver;

public class BaseTest {
	
	CommonDriver cmnDriver;
	String url;
	
	WebDriver driver;
	
	LoginPage loginpage;
	
	String configFileName;
	String currentWorkingDirectory;
	
	Properties configProperty;
	
	String reportFileName;
	ReportUtils reportUtils;
	
	ScreenshotUtils screenshotutils;
	
	@BeforeSuite
	public void preSetup() throws Exception {
		
		currentWorkingDirectory = System.getProperty("user.dir");
		
		configFileName = currentWorkingDirectory + "/config/config.properties";
		reportFileName = currentWorkingDirectory + "/reports/guru99TestReport.html";
		
		configProperty = ConfigUtils.readProperties(configFileName);
		reportUtils  = new ReportUtils(reportFileName);
		
		
	}
	
	@BeforeClass
	public void setup() throws Exception {
		url = configProperty.getProperty("baseUrl");
		
		String browserType = configProperty.getProperty("browserType");
		
		cmnDriver = new CommonDriver(browserType);
		
		driver = cmnDriver.getDriver();
		
		loginpage = new LoginPage(driver);
		
		screenshotutils = new ScreenshotUtils(driver);
		
		cmnDriver.navigateToUrl(url);
	}
	
	@AfterMethod
	public void postTestActions(ITestResult result) throws Exception {
		
		String testcasename = result.getName();
		long executionTime = System.currentTimeMillis();
		
		String screenshotFilename = currentWorkingDirectory + "/screenshots/" + testcasename + executionTime + ".jpeg";
		
		if(result.getStatus() == ITestResult.FAILURE) {
			
			reportUtils.addTestLog(Status.FAIL, "One or more steps failed!");
			screenshotutils.captureAndSaveScreenshot(screenshotFilename);
			
			reportUtils.attachScreenshotToReports(screenshotFilename);
		}
	}
	
	@AfterClass
	public void tearDown() {
		cmnDriver.closeBrowser();
	}
	
	@AfterSuite
	public void postTeatDown() {
		
		reportUtils.flushReport();
	}
	
}

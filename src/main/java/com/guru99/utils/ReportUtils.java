package com.guru99.utils;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportUtils {
	
	ExtentHtmlReporter htmlReport;
	
	ExtentReports extentReports;
	
	ExtentTest extentTest;
	
	public ReportUtils(String htmlReportFileName) {
		htmlReportFileName = htmlReportFileName.trim();
		
		htmlReport = new ExtentHtmlReporter(htmlReportFileName);
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(htmlReport);
		
		
	}
	
	public void createATestCase(String testCaseName) {
		
		extentTest = extentReports.createTest(testCaseName);
	}
	
	public void addTestLog(Status status, String comment) {
		
		extentTest.log(status, comment);
		
	}
	
	public void flushReport() {
		
		extentReports.flush();
	}
	
	public void attachScreenshotToReports(String filename) throws IOException {
		
		extentTest.addScreenCaptureFromPath(filename);
	}
	
	
	

}

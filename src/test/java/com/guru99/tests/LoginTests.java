package com.guru99.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;


public class LoginTests extends BaseTest{

	@Parameters({"username", "userPassword"})
	@Test
	public void verifyUserLoginWithCorrectCridentials(String username, String password) {
		
		reportUtils.createATestCase("verify User Login With Correct Cridentials");
		
		reportUtils.addTestLog(Status.INFO, "Performing Login");
		
		loginpage.loginToApplication(username, password);
		
		String expectedTitle = "Guru99 Bank Manager HomePge";
		String actualTitle = cmnDriver.getTitle();
		
		reportUtils.addTestLog(Status.INFO, "Comparing the title");
		Assert.assertEquals(actualTitle, expectedTitle);
	}
		
}

package com.guru99.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementation.ElementControl;

public class BasePage {
	
	WebDriver driver;
	public ElementControl elementControl;
	
	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		elementControl = new ElementControl(driver);
	}
}

package com.meganexus.trpages;

import org.testng.annotations.Parameters;

import com.meganexus.utills.ApplicationProperties;
import com.meganexus.utills.BasicUtill;

public class LoginPage extends BasicUtill {

	@Parameters("browser")
	public void openApps() {
		openBrowser(ApplicationProperties.getProperty("browser"));
		getDriver().get(ApplicationProperties.getProperty("CMS_URL"));
		
	}

	public void login() {
		waitForElementVisible("id", "Username");
		getElement("id", "Username").clear();
		getElement("id", "Username").sendKeys(ApplicationProperties.getProperty("Username1"));
		waitABit(100);
		getElement("id", "Password").clear();
		getElement("id", "Password").sendKeys(ApplicationProperties.getProperty("Password1"));
		waitABit(100);
		getElement("xpath", "LoginBtn").click();
		pageToLoadImplicitWait(100);
	}
}

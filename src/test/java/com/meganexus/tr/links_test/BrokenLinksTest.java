package com.meganexus.tr.links_test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.meganexus.trpages.HomePage;
import com.meganexus.trpages.LoginPage;
import com.meganexus.trpages.PendingTransferPage;
import com.meganexus.utills.BasicUtill;
import com.meganexus.utills.ExtentManager;

public class BrokenLinksTest {
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	PendingTransferPage pending = new PendingTransferPage();
	

	@BeforeTest
	public void openBrowserAndApps() {
		loginPage.openApps();

	}

	@BeforeMethod
	public void loginTest() {
		loginPage.login();
	}

	@Test(priority = 0)
	public void acceptoffenderTest() {
		ExtentManager.test = ExtentManager.extent.createTest("Test Case d", "Failed test case");
		pending.clickOnSuAdminTab();
		pending.clickonPendingTransferTab();
		pending.searchOffenderInPendingTransfer();
		pending.viewOffenderInPendingTransfer();
		pending.acceptOffnder();
	}

	@Test(priority = 1)
	public void linkTest() {
		

		homePage.viewOffender();
		List<WebElement> links = BasicUtill.getDriver().findElements(By.tagName("a"));

		System.out.println("Total links are " + links.size());

		for (int i = 0; i < links.size(); i++) {
			WebElement element = links.get(i);
			// By using "href" attribute, we could get the url of the requried
			// link
			String url = element.getAttribute("href");
			// calling verifyLink() method here. Passing the parameter as url
			// which we collected in the above link
			// See the detailed functionality of the verifyLink(url) method
			// below
			BasicUtill.verifyLink(url);

		}
	}
	@AfterMethod(alwaysRun = true)
	public void logoutFromApps(){
		homePage.logout();
	}

	@AfterTest(alwaysRun = true)
	public void closeBrowser() {
		BasicUtill.tearDown();
	}

}
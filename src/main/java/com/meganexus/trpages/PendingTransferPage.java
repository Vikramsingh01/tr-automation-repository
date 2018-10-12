package com.meganexus.trpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.meganexus.utills.ApplicationProperties;
import com.meganexus.utills.BasicUtill;

public class PendingTransferPage extends BasicUtill {
	public void clickOnSuAdminTab() {
		waitForElementVisible("id", "SUADminTab");
		scrollToClickAnElement("id", "SUADminTab");
		waitABit(100);
	}

	public void clickonPendingTransferTab() {
		waitForElementVisible("id", "PendingTransferTab");
		scrollToClickAnElement("id", "PendingTransferTab");
		waitABit(100);

	}

	public void searchOffenderInPendingTransfer() {
		waitForElement("id", "PendingTransfershdr");
		scrollToClickAnElement("id", "Filterbtn");
		waitABit(100);
		waitForElement("id", "PendingTransferCRN_TextArea");
		getElement("id", "PendingTransferCRN_TextArea").sendKeys(ApplicationProperties.getProperty("CRN_No"));
		waitABit(100);
		scrollToClickAnElement("xpath", "SearchBtnInPendingTransfer");
		waitABit(100);

	}

	public void viewOffenderInPendingTransfer() {
		String viewOffnderXpath = "//td[text()='" + ApplicationProperties.getProperty("CRN_No")
				+ "']/following-sibling::td/span/button[@id='pendingTransfer_view0']";
		// waitForElement("id", "ViewOffenderBtnInPendingTransfer");
		// waitForElementPresent(By.id("ViewOffenderBtnInPendingTransfer"));
		scrollToClickAnElement("id", "ViewOffenderBtnInPendingTransfer");
		waitABit(100);
	}

	public void acceptOffnder() {
		waitForElement("xpath", "AllocationInPendingTransfer");
		scrollToViewAnElement("xpath", "ComponentinPendingTransfer");
		selectAnElementWithIndex("id", "TeamInPendingTransfer", 1);
		waitABit(100);
		selectAnElementWithIndex("id", "OfficerInPendingTransfer", 1);
		waitABit(100);
		List<WebElement> list = getDriver().findElements(By.xpath("//input[@id='status']"));
		for (int i = 0; i < list.size(); i++) {
			list.get(i).click();
		}
		selectAnElementWithIndex("xpath", "BandInPendingTransfer", 1);
		waitABit(100);
		getElement("id", "SubmitBtnInPendingTransfer").click();
		waitABit(100);
	}

}

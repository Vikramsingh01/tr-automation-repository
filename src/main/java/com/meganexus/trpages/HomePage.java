package com.meganexus.trpages;

import com.meganexus.utills.ApplicationProperties;
import com.meganexus.utills.BasicUtill;

public class HomePage extends BasicUtill {
	public void viewOffender() {
		waitForElement("id", "CrcServiceUserTab");
		scrollToClickAnElement("id", "SUManagementTab");
		waitABit(100);
		getElement("id", "ServiceUserTab").click();
		waitABit(100);
		scrollToClickAnElement("id", "CrcServiceUserTab");
		waitABit(100);
		getElement("id", "Filterbtn").click();
		waitABit(100);
		getElement("id", "CRN_TextAreaInCRCServiceUserPage").sendKeys(ApplicationProperties.getProperty("CRN_No"));
		waitABit(100);
		getElement("id", "SearchBtnInServiceUserPage").click();
		waitABit(100);
		waitForElement("xpath", "ViewOffenderBtn");
		scrollToClickAnElement("xpath", "ViewOffenderBtn");
		waitABit(100);
		waitForElement("id", "ProfileTab");
	}

	public void logout() {
		waitForElementVisible("xpath", "AccountBtn");
		getElement("xpath", "AccountBtn").click();
		waitABit(100);
		getElement("xpath", "LogoutBtn").click();
		waitABit(100);

	}

}

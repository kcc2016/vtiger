package LeadsModule;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.DataProviderClass;
import pomRepository.HomePage;
import pomRepository.LeadsPage;

public class DeleteLeadUsingDelLInkTest extends BaseClass {
	@Test(dataProvider = "deleteLead", dataProviderClass = DataProviderClass.class)
	public void deleteLeadUsingDelLinkAndVerifyTest(String firstName, String lastName) throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeadsModule();
		logger.info("The user has navigated to Leads Module");

		LeadsPage leadsPage = new LeadsPage(driver);
		while (leadsPage.countLeads(firstName, lastName) > 0) {
			leadsPage.clickOnDelLink(firstName, lastName);
			logger.info("The user is trying to delete " + firstName + " " + lastName);
			Thread.sleep(1000);
			wUtils.acceptAlertPopup(driver);
			logger.info("The user has accepted the alert popup");
			Thread.sleep(3000);
		}
		Assert.assertTrue(leadsPage.countLeads(firstName, lastName) == 0, "Test Failed");
		logger.info("Test Passed");

		leadsPage.clickOnHomeButton();
		logger.info("The user has navigated to home page");
	}
}

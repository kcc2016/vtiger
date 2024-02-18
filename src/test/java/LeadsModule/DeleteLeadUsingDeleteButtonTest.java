package LeadsModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import genericUtility.ITestListenerImplementationClass;
import pomRepository.HomePage;
import pomRepository.LeadsPage;

@Listeners(ITestListenerImplementationClass.class)
public class DeleteLeadUsingDeleteButtonTest extends BaseClass {
	@Test
	public void deleteLeadAndVerifyTest() throws EncryptedDocumentException, IOException, InterruptedException {
		HomePage homePage = new HomePage(driver);
		LeadsPage leadsPage = new LeadsPage(driver);

		homePage.clickOnLeadsModule();
		System.out.println("The user has clicked on Leads Module");

		String firstName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 6, 0);
		String lastName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 6, 1);
		String company = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 6, 2);

		leadsPage.selectLeads(firstName, lastName, company);
		System.out.println("The user has selected the leads");
		leadsPage.clickOnDeleteButton();
		System.out.println("The user has clicked on delete button");
		Thread.sleep(2000);
		wUtils.acceptAlertPopup(driver);
		System.out.println("The user has accepted the delete popup");
		Thread.sleep(2000);

		Assert.assertEquals(leadsPage.countLeads(firstName, lastName, company), 0, "Test Failed");
		System.out.println("Test Passed");

		leadsPage.clickOnHomeButton();
		System.out.println("The user has navigated to home page");
	}
}

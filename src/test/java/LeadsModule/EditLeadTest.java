package LeadsModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import pomRepository.HomePage;
import pomRepository.LeadsPage;

public class EditLeadTest extends BaseClass {
	@Test
	public void editLeadAndVerifyTest() throws EncryptedDocumentException, IOException, InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeadsModule();
		logger.info("The user has clicked on Leads Module");

		LeadsPage leadsPage = new LeadsPage(driver);
		String oldFirstName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 0);
		String oldLastName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 1);
		String newFirstName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 2);
		String newLastName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 3);
		int numOfOldRecords = leadsPage.countLeads(oldFirstName, oldLastName);
		int numOfNewRecords = leadsPage.countLeads(newFirstName, newLastName);
		leadsPage.selectLeads(oldFirstName, oldLastName);
		logger.info("The user has selected the " + numOfOldRecords + " leads that he/she wants to edit");
		if (numOfOldRecords > 0) {
			leadsPage.clickOnMassEditButton();
			logger.info("The user has clicked on mass edit button");
			Thread.sleep(3000);
			leadsPage.clickOnFirstNameMassEditCheckBox();
			leadsPage.enterFirstNameInMassEdit(newFirstName);
			leadsPage.clickOnLastNameMassEditCheckBox();
			leadsPage.enterLastNameInMassEdit(newLastName);
			leadsPage.clickOnMassEditSaveButton();
			logger.info("The user has entered new details and clicked on save button");
			Thread.sleep(3000);
		}

		if (leadsPage.countLeads(newFirstName, newLastName) == (numOfOldRecords + numOfNewRecords)
				&& leadsPage.countLeads(oldFirstName, oldLastName) == 0) {
			eUtils.writeStringDataInExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 4, "Pass");
			eUtils.fillColorInExcelCell(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 4, IPathConstant.GREEN_COLOR_KEY);
		} else {
			eUtils.writeStringDataInExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 4, "Fail");
			eUtils.fillColorInExcelCell(IPathConstant.LEADS_MODULE_SHEET_NAME, 12, 4, IPathConstant.RED_COLOR_KEY);
		}

		Assert.assertEquals(leadsPage.countLeads(newFirstName, newLastName), (numOfOldRecords + numOfNewRecords),
				"Test Failed");
		Assert.assertEquals(leadsPage.countLeads(oldFirstName, oldLastName), 0, "Test Failed");

		logger.info("Test Passed");
		leadsPage.clickOnHomeButton();
		logger.info("The user has navigated to home screen");
	}
}

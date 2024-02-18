package LeadsModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import genericUtility.ITestListenerImplementationClass;
import pomRepository.CreateNewLeadPage;
import pomRepository.HomePage;
import pomRepository.LeadInformationPage;
import pomRepository.LeadsPage;

@Listeners(ITestListenerImplementationClass.class)
public class CreateLeadTest extends BaseClass {
	@Test
	public void createAndVerifyLeadTest() throws EncryptedDocumentException, IOException {
		HomePage homePage = new HomePage(driver);
		LeadsPage leadsPage = new LeadsPage(driver);
		CreateNewLeadPage createNewLeadPage = new CreateNewLeadPage(driver);
		LeadInformationPage leadInformationPage = new LeadInformationPage(driver);

		homePage.clickOnLeadsModule();
		System.out.println("The user has navigated to Leads Page");
		leadsPage.clickOnCreateLeadButton();
		System.out.println("The user has clicked on create new lead button");

		String salutation = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 0);
		String firstName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 1);
		String lastName = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 2);
		String company = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 3);
		String leadSource = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 4);
		int annualRevenue = eUtils.fetchIntegerDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 5);
		String rating = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 6);
		String assignedToRadioButton = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 7);
		String assignedToDropDown = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 8);
		String country = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 9);

		createNewLeadPage.selectFirstNameSalutation(salutation);
		createNewLeadPage.enterFirstName(firstName);
		createNewLeadPage.enterLastName(lastName);
		createNewLeadPage.enterCompany(company);
		createNewLeadPage.selectLeadSource(leadSource);
		createNewLeadPage.enterAnnualRevenue(annualRevenue);
		createNewLeadPage.selectRating(rating);
		createNewLeadPage.selectAssignedToRadioButtonAndDropDown(assignedToRadioButton, assignedToDropDown);
		createNewLeadPage.enterCountry(country);
		createNewLeadPage.clickOnSaveButton();
		System.out.println("The user has clicked on save button");

		if (leadInformationPage.verifyLeadInformationText(lastName)) {
			eUtils.writeStringDataInExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 10, IPathConstant.PASS_KEY);
			eUtils.fillColorInExcelCell(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 10, IPathConstant.GREEN_COLOR_KEY);
		} else {
			eUtils.writeStringDataInExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 10, IPathConstant.FAIL_KEY);
			eUtils.fillColorInExcelCell(IPathConstant.LEADS_MODULE_SHEET_NAME, 2, 10, IPathConstant.RED_COLOR_KEY);
		}

		Assert.assertTrue(leadInformationPage.verifyLeadInformationText(lastName), "Test Failed: Lead is not created");
		System.out.println("Test Passed: Lead creation is successful");

		leadInformationPage.clickOnHomeButton();
		System.out.println("The user has navigated back to home screen");
	}
}

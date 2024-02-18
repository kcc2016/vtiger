package VendorsModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import genericUtility.ITestListenerImplementationClass;
import pomRepository.CreateNewVendorPage;
import pomRepository.HomePage;
import pomRepository.VendorInformationPage;
import pomRepository.VendorsPage;

@Listeners(ITestListenerImplementationClass.class)
public class CreateVendorTest extends BaseClass {
	@Test
	public void createVendorAndVerifyTest() throws EncryptedDocumentException, IOException {
		HomePage homePage = new HomePage(driver);
		VendorsPage vendorsPage = new VendorsPage(driver);
		CreateNewVendorPage createNewVendorPage = new CreateNewVendorPage(driver);
		VendorInformationPage vendorInformationPage = new VendorInformationPage(driver);

		homePage.clickOnVendorsModule();
		System.out.println("The user has navigated to Vendors Module");

		vendorsPage.clickOnCreateVendorButton();
		System.out.println("The user has clicked on create vendor button");

		String vendorName = eUtils.fetchStringDataFromExcel(IPathConstant.VENDORS_MODULE_SHEET_NAME, 2, 0);
		String email = eUtils.fetchStringDataFromExcel(IPathConstant.VENDORS_MODULE_SHEET_NAME, 2, 1);
		String glacct = eUtils.fetchStringDataFromExcel(IPathConstant.VENDORS_MODULE_SHEET_NAME, 2, 2);
		String city = eUtils.fetchStringDataFromExcel(IPathConstant.VENDORS_MODULE_SHEET_NAME, 2, 3);
		String country = eUtils.fetchStringDataFromExcel(IPathConstant.VENDORS_MODULE_SHEET_NAME, 2, 4);

		createNewVendorPage.enterVendorName(vendorName);
		createNewVendorPage.enterEmail(email);
		createNewVendorPage.selectGLAcct(glacct);
		createNewVendorPage.enterCity(city);
		createNewVendorPage.enterCountry(country);
		createNewVendorPage.clickOnSaveButton();
		System.out.println("The user has entered vendor details and clicked on Save Button");

		Assert.assertTrue(vendorInformationPage.verifyVendorInformationText(vendorName), "Test Failed");
		System.out.println("Test Passed");

		vendorInformationPage.clickOnHomeButton();
		System.out.println("The user has navigated back to home screen");
	}
}

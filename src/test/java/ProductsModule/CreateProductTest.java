package ProductsModule;

import java.awt.AWTException;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IPathConstant;
import genericUtility.ITestListenerImplementationClass;
import pomRepository.CreateNewProductPage;
import pomRepository.HomePage;
import pomRepository.ProductInformationPage;
import pomRepository.ProductsPage;

@Listeners(ITestListenerImplementationClass.class)
public class CreateProductTest extends BaseClass {
	@Test
	public void createProductAndVerifyTest()
			throws EncryptedDocumentException, IOException, AWTException, InterruptedException {
		HomePage homePage = new HomePage(driver);
		ProductsPage productsPage = new ProductsPage(driver);
		CreateNewProductPage createNewProductPage = new CreateNewProductPage(driver);
		ProductInformationPage productInformationPage = new ProductInformationPage(driver);

		homePage.clickOnProductsModule();
		System.out.println("The user has clicked on Products Module");

		productsPage.clickOnCreateNewProductButton();
		System.out.println("The user has clicked on create product button");

		String productName = eUtils.fetchStringDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 0);
		LocalDateTime salesStartDate = eUtils.fetchLocalDateTimeDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME,
				2, 1);
		String productCategory = eUtils.fetchStringDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 2);
		LocalDateTime salesEndDate = eUtils.fetchLocalDateTimeDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2,
				3);
		String vendorName = eUtils.fetchStringDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 4);
		String vendorNameWindowPartialTitle = eUtils.fetchStringDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME,
				2, 5);
		int unitPrice = eUtils.fetchIntegerDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 6);
		int vat = eUtils.fetchIntegerDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 7);
		int qtyInStock = eUtils.fetchIntegerDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 8);
		String fileRelativePath = eUtils.fetchStringDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 9);

		createNewProductPage.enterProductName(productName);
		createNewProductPage.enterSalesStartDate(salesStartDate);
		createNewProductPage.selectProductCategory(productCategory);
		createNewProductPage.enterSalesEndDate(salesEndDate);
		createNewProductPage.addVendorName(vendorName, vendorNameWindowPartialTitle);
		createNewProductPage.enterUnitPrice(unitPrice);
		createNewProductPage.enterVAT(vat);
		createNewProductPage.enterQtyInStock(qtyInStock);
		createNewProductPage.uploadImage(fileRelativePath);
		createNewProductPage.clickOnSaveButton();
		System.out.println("The user has entered details and clicked on save button");
		Thread.sleep(2000);

		Assert.assertTrue(productInformationPage.verifyProductInformationText(productName), "Test Failed");
		System.out.println("Test Passed");

		productInformationPage.clickOnHomeButton();
		System.out.println("The user has navigated to home page");
	}
}

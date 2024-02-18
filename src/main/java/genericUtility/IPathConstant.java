package genericUtility;

public interface IPathConstant {
	String PROPERTY_FILE_PATH = ".\\src\\test\\resources\\testData\\vtigerPropertyData.properties";
	String EXCEL_FILE_PATH = ".\\src\\test\\resources\\testData\\vtigerTestData.xlsx";

	String BROWSER_KEY = "browser";
	String BROWSER_VALUE_CHROME = "chrome";
	String BROWSER_VALUE_EDGE = "edge";

	String URL_KEY = "url";
	String USERNAME_KEY = "username";
	String PASSWORD_KEY = "password";

	long IMPLICIT_WAIT_DURATION = 10L;

	String LEADS_MODULE_SHEET_NAME = "LeadsModule";
	String VENDORS_MODULE_SHEET_NAME = "VendorsModule";
	String PRODUCTS_MODULE_SHEET_NAME = "ProductsModule";
	String LOGIN_MODULE_SHEET_NAME = "LoginModule";

	String SCREENSHOT_FOLDER_PATH = ".\\src\\test\\resources\\screenshots\\";
	String EXTENT_REPORTS_FOLDER_PATH = ".\\ExtentReports\\";

	String GREEN_COLOR_KEY = "GREEN";
	String RED_COLOR_KEY = "RED";

	String PASS_KEY = "PASS";
	String FAIL_KEY = "FAIL";

	String VALID_KEY = "valid";
	String INVALID_KEY = "invalid";
}

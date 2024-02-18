package LoginModule;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.DataProviderClass;
import genericUtility.FileUtility;
import genericUtility.IPathConstant;
import genericUtility.WebDriverUtility;
import pomRepository.HomePage;
import pomRepository.LoginPage;

public class LoginTest {
	Logger logger;
	WebDriverUtility wUtils = new WebDriverUtility();
	FileUtility fUtils = new FileUtility();
	WebDriver driver;

	@BeforeClass
	public void bc() throws IOException {
		logger = LogManager.getLogger(this.getClass());
		driver = new ChromeDriver();
		BaseClass.sDriver = driver;
		logger.info("Browser launched");
		wUtils.maximizeWindow(driver);
		logger.info("Browser maximized");
		wUtils.implicitWait(driver, 10L);

		driver.get(fUtils.fetchDataFromPropertyFile(IPathConstant.URL_KEY));
		logger.info("Navigated to URL: " + fUtils.fetchDataFromPropertyFile(IPathConstant.URL_KEY));
	}

	@AfterClass
	public void ac() {
		driver.quit();
	}

	@Test(dataProvider = "loginTest", dataProviderClass = DataProviderClass.class)
	public void LoginDataDrivenTest(String username, String password, String status) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		logger.info("User - " + username + " has tried to login with password - " + password);

		if (status.equalsIgnoreCase(IPathConstant.VALID_KEY)) {
			if (driver.getTitle().contains("Home")) {
				logger.info("Test Passed");
				Assert.assertTrue(true);

				HomePage homePage = new HomePage(driver);
				homePage.signout();
			} else {
				logger.info("Test Failed");
				Assert.fail();
			}
		} else {
			if (driver.getTitle().contains("Home")) {
				logger.info("Test Failed");

				HomePage homePage = new HomePage(driver);
				homePage.signout();

				Assert.fail();
			} else {
				logger.info("Test Passed");
				Assert.assertTrue(true);
			}
		}
	}
}

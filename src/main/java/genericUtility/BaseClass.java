package genericUtility;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import pomRepository.HomePage;
import pomRepository.LoginPage;

public class BaseClass {
	public WebDriver driver;
	public FileUtility fUtils = new FileUtility();
	public WebDriverUtility wUtils = new WebDriverUtility();
	public ExcelUtility eUtils = new ExcelUtility();
	public static WebDriver sDriver;
	public ChromeOptions chromeOptions;
	public EdgeOptions edgeOptions;
	public Logger logger;

	@BeforeSuite
	public void bs() {
		System.out.println("Database connection established");
	}

	@BeforeClass
	public void bc() throws IOException {
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");

		edgeOptions = new EdgeOptions();
		edgeOptions.addArguments("--headless");

		logger = LogManager.getLogger(this.getClass());

		String browser = fUtils.fetchDataFromPropertyFile(IPathConstant.BROWSER_KEY);
		if (browser.equals(IPathConstant.BROWSER_VALUE_CHROME))
			driver = new ChromeDriver();
		else if (browser.equals(IPathConstant.BROWSER_VALUE_EDGE))
			driver = new EdgeDriver();
		sDriver = driver;
		logger.info("Browser has opened");

		wUtils.maximizeWindow(driver);
		wUtils.implicitWait(driver, IPathConstant.IMPLICIT_WAIT_DURATION);

		String url = fUtils.fetchDataFromPropertyFile(IPathConstant.URL_KEY);
		driver.get(url);
		logger.info("Navigated to the URL - " + url);
	}

	@BeforeMethod
	public void bm() throws IOException {
		LoginPage loginPage = new LoginPage(driver);

		String username = fUtils.fetchDataFromPropertyFile(IPathConstant.USERNAME_KEY);
		String password = fUtils.fetchDataFromPropertyFile(IPathConstant.PASSWORD_KEY);

		loginPage.login(username, password);
		logger.info("User '" + username + "' has logged in");
	}

	@AfterMethod
	public void am() {
		HomePage homePage = new HomePage(driver);
		homePage.signout();
		logger.info("The user has signed out");
	}

	@AfterClass
	public void ac() {
		driver.quit();
		logger.info("The browser has closed");
	}

	@AfterSuite
	public void as() {
		logger.info("Database connection closed");
	}
}

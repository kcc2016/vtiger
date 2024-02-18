package pomRepository;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import genericUtility.WebDriverUtility;

public class CreateNewProductPage {
	WebDriver driver;
	WebDriverUtility wUtils = new WebDriverUtility();
	Robot robot;

	public CreateNewProductPage(WebDriver driver) throws AWTException {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		robot = new Robot();
	}

	@FindBy(name = "productname")
	private WebElement productNameTextField;

	@FindBy(name = "sales_start_date")
	private WebElement salesStartDateTextField;

	@FindBy(name = "productcategory")
	private WebElement productCategoryDropDown;

	@FindBy(id = "jscal_trigger_sales_end_date")
	private WebElement salesEndDateCalendarButton;

	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement addVendorNameButton;

	@FindBy(id = "unit_price")
	private WebElement unitPriceTextField;

	@FindBy(name = "tax1_check")
	private WebElement vatCheckBox;

	@FindBy(id = "tax1")
	private WebElement vatTextField;

	@FindBy(id = "qtyinstock")
	private WebElement qtyInStockTextField;

	@FindBy(xpath = "//input[@id='my_file_element']")
	private WebElement productImageUploadButton;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public void enterProductName(String productName) {
		productNameTextField.sendKeys(productName);
	}

	public void enterSalesStartDate(LocalDateTime localDateTime) {
		String localDateTimeString = String.valueOf(localDateTime.getYear()) + "-"
				+ String.valueOf(localDateTime.getMonthValue()) + "-" + String.valueOf(localDateTime.getDayOfMonth());
		salesStartDateTextField.sendKeys(localDateTimeString);
	}

	public void selectProductCategory(String productCategory) {
		Select s = new Select(productCategoryDropDown);
		s.selectByValue(productCategory);
	}

	public void enterSalesEndDate(LocalDateTime localDateTime) {
		salesEndDateCalendarButton.click();
		LocalDateTime currentDateTime = LocalDateTime.now();

		int year = localDateTime.getYear();
		int month = localDateTime.getMonthValue();
		int day = localDateTime.getDayOfMonth();

		int currentYear = currentDateTime.getYear();
		int currentMonth = currentDateTime.getMonthValue();

		WebElement previousYear = driver.findElement(By.xpath("//tr[@class='headrow']//child::td[1]"));
		WebElement previousMonth = driver.findElement(By.xpath("//tr[@class='headrow']//child::td[2]"));
		WebElement nextMonth = driver.findElement(By.xpath("//tr[@class='headrow']//child::td[4]"));
		WebElement nextYear = driver.findElement(By.xpath("//tr[@class='headrow']//child::td[5]"));

		if (year > currentYear) {
			int difference = year - currentYear;
			for (int i = 1; i <= difference; i++) {
				nextYear.click();
			}
		} else {
			int difference = currentYear - year;
			for (int i = 1; i <= difference; i++) {
				previousYear.click();
			}
		}

		if (month > currentMonth) {
			int difference = month - currentMonth;
			for (int i = 1; i <= difference; i++) {
				nextMonth.click();
			}
		} else {
			int difference = currentMonth - month;
			for (int i = 1; i <= difference; i++) {
				previousMonth.click();
			}
		}

		driver.findElement(By.xpath(
				"//td[@class='day' or @class='day weekend' or @class='day selected today'][text()='" + day + "']"))
				.click();
	}

	public void addVendorName(String vendorName, String partialWindowTitle) {
		String titleOfOrigWindow = driver.getTitle();
		addVendorNameButton.click();
		wUtils.switchToAnotherWindow(driver, partialWindowTitle);
		driver.findElement(By.xpath("(//a[text()='" + vendorName + "'])[1]")).click();
		wUtils.switchToAnotherWindow(driver, titleOfOrigWindow);
	}

	public void enterUnitPrice(int unitPrice) {
		unitPriceTextField.sendKeys(String.valueOf(unitPrice));
	}

	public void enterVAT(int vat) throws InterruptedException {
		vatCheckBox.click();
		vatTextField.clear();
		Thread.sleep(1000);
		wUtils.acceptAlertPopup(driver);
		Thread.sleep(1000);
		vatTextField.sendKeys(String.valueOf(vat));
	}

	public void enterQtyInStock(int qtyInStock) {
		qtyInStockTextField.sendKeys(String.valueOf(qtyInStock));
	}

	public void uploadImage(String relativeFilePath) {
		File f = new File(relativeFilePath);
		String absoluteFilePath = f.getAbsolutePath();
		productImageUploadButton.sendKeys(absoluteFilePath);
	}

	public void clickOnSaveButton() {
		saveButton.click();
	}
}

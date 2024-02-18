package pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewLeadPage {
	private WebDriver driver;

	public CreateNewLeadPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "salutationtype")
	private WebElement salutationDropDown;

	@FindBy(name = "firstname")
	private WebElement firstNameTextField;

	@FindBy(name = "lastname")
	private WebElement lastNameTextField;

	@FindBy(name = "company")
	private WebElement companyTextField;

	@FindBy(name = "leadsource")
	private WebElement leadSourceDropDown;

	@FindBy(name = "annualrevenue")
	private WebElement annualRevenueTextField;

	@FindBy(name = "rating")
	private WebElement ratingDropDown;

	@FindBy(name = "country")
	private WebElement countryTextField;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public void selectFirstNameSalutation(String salutation) {
		Select s = new Select(salutationDropDown);
		s.selectByValue(salutation);
	}

	public void enterFirstName(String firstName) {
		firstNameTextField.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		lastNameTextField.sendKeys(lastName);
	}

	public void enterCompany(String company) {
		companyTextField.sendKeys(company);
	}

	public void selectLeadSource(String leadSource) {
		Select s = new Select(leadSourceDropDown);
		s.selectByValue(leadSource);
	}

	public void enterAnnualRevenue(int annualRevenue) {
		String annualRevenueString = String.valueOf(annualRevenue);
		annualRevenueTextField.clear();
		annualRevenueTextField.sendKeys(annualRevenueString);
	}

	public void selectRating(String rating) {
		Select s = new Select(ratingDropDown);
		s.selectByValue(rating);
	}

	public void selectAssignedToRadioButtonAndDropDown(String assignedToRadioButton, String assignedToDropDown) {
		if (assignedToRadioButton.equalsIgnoreCase("user")) {
			driver.findElement(By.xpath("//input[@name='assigntype'][@value='U']")).click();
			Select s = new Select(driver.findElement(By.name("assigned_user_id")));
			s.selectByValue("1");
		} else if (assignedToRadioButton.equalsIgnoreCase("group")) {
			driver.findElement(By.xpath("//input[@name='assigntype'][@value='T']")).click();
			Select s = new Select(driver.findElement(By.name("assigned_group_id")));
			s.selectByVisibleText(assignedToDropDown);
		}
	}

	public void enterCountry(String country) {
		countryTextField.sendKeys(country);
	}

	public void clickOnSaveButton() {
		saveButton.click();
	}
}

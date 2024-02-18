package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewVendorPage {
	public CreateNewVendorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "vendorname")
	private WebElement vendorNameTextField;

	@FindBy(id = "email")
	private WebElement emailTextField;

	@FindBy(name = "glacct")
	private WebElement glacctDropDown;

	@FindBy(id = "city")
	private WebElement cityTextField;

	@FindBy(id = "country")
	private WebElement countryTextField;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public void enterVendorName(String vendorName) {
		vendorNameTextField.sendKeys(vendorName);
	}

	public void enterEmail(String email) {
		emailTextField.sendKeys(email);
	}

	public void selectGLAcct(String glacct) {
		Select s = new Select(glacctDropDown);
		s.selectByValue(glacct);
	}

	public void enterCity(String city) {
		cityTextField.sendKeys(city);
	}

	public void enterCountry(String country) {
		countryTextField.sendKeys(country);
	}

	public void clickOnSaveButton() {
		saveButton.click();
	}
}

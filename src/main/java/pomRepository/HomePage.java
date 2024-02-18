package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	Actions act;
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.act = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Leads")
	private WebElement leadsModule;

	@FindBy(xpath = "//img[@src='themes/softed/images/Home.PNG']")
	private WebElement homeButton;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement dropdownButton;

	@FindBy(linkText = "Sign Out")
	private WebElement signout_option;

	@FindBy(xpath = "//a[@href='javascript:;'][normalize-space()='More']")
	private WebElement moreDropDown;

	@FindBy(xpath = "//a[@name='Vendors']")
	private WebElement vendorsModule;

	@FindBy(linkText = "Products")
	private WebElement productsModule;

	public void signout() {
		act.moveToElement(dropdownButton).perform();
		signout_option.click();
	}

	public void clickOnLeadsModule() {
		leadsModule.click();
	}

	public void clickOnHomeButton() {
		homeButton.click();
	}

	public void clickOnVendorsModule() {
		act.moveToElement(moreDropDown).perform();
		vendorsModule.click();
	}

	public void clickOnProductsModule() {
		productsModule.click();
	}
}

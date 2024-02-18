package pomRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	private WebDriver driver;

	public LeadsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Lead...']")
	private WebElement createLeadButton;

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//img[@src='themes/softed/images/Home.PNG']")
	private WebElement homeButton;

	@FindBy(xpath = "//input[@value='Mass Edit']")
	private WebElement massEditButton;

	@FindBy(name = "firstname_mass_edit_check")
	private WebElement firstNameMassEditCheckBox;

	@FindBy(name = "lastname_mass_edit_check")
	private WebElement lastNameMassEditCheckBox;

	@FindBy(css = "[name='firstname']")
	private WebElement firstNameMassEditTextField;

	@FindBy(css = "[name='lastname']")
	private WebElement lastNameMassEditTextField;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement massEditSaveButton;

	public void clickOnCreateLeadButton() {
		createLeadButton.click();
	}

	public void selectLeads(String firstName, String lastName, String company) {
		List<WebElement> l = driver.findElements(
				By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td/child::a[text()='" + firstName
						+ "']/parent::td/following-sibling::td[contains(text(),'" + company
						+ "')]/parent::tr/descendant::input[1]"));
		for (WebElement we : l) {
			we.click();
		}
	}

	public void clickOnDeleteButton() {
		deleteButton.click();
	}

	public int countLeads(String firstName, String lastName, String company) {
		List<WebElement> l = driver.findElements(
				By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td/child::a[text()='" + firstName
						+ "']/parent::td/following-sibling::td[contains(text(),'" + company
						+ "')]/parent::tr/descendant::input[1]"));
		return l.size();
	}

	public void clickOnHomeButton() {
		homeButton.click();
	}

	public void selectLeads(String firstName, String lastName) {
		List<WebElement> l = driver.findElements(
				By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td/child::a[text()='" + firstName
						+ "']/parent::td/parent::tr/descendant::input[1]"));
		for (WebElement we : l) {
			we.click();
		}
	}

	public int countLeads(String firstName, String lastName) {
		List<WebElement> l = driver.findElements(
				By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td/child::a[text()='" + firstName
						+ "']/parent::td/parent::tr/descendant::input[1]"));
		return l.size();
	}

	public void clickOnMassEditButton() {
		massEditButton.click();
	}

	public void clickOnFirstNameMassEditCheckBox() {
		firstNameMassEditCheckBox.click();
	}

	public void clickOnLastNameMassEditCheckBox() {
		lastNameMassEditCheckBox.click();
	}

	public void enterFirstNameInMassEdit(String firstName) {
		firstNameMassEditTextField.sendKeys(firstName);
	}

	public void enterLastNameInMassEdit(String lastName) {
		lastNameMassEditTextField.sendKeys(lastName);
	}

	public void clickOnMassEditSaveButton() {
		massEditSaveButton.click();
	}

	public void clickOnDelLink(String firstName, String lastName) {
		driver.findElement(By.xpath("//a[text()='" + lastName + "']/parent::td/following-sibling::td/a[text()='"
				+ firstName + "']/parent::td/following-sibling::td/a[text()='del']")).click();
	}

}

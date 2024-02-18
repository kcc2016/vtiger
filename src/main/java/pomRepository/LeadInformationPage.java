package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInformationPage {
	public LeadInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(), 'Lead Information')]")
	private WebElement leadInformationText;

	@FindBy(xpath = "//img[@src='themes/softed/images/Home.PNG']")
	private WebElement homeButton;

	public boolean verifyLeadInformationText(String lastName) {
		return leadInformationText.getText().contains(lastName);
	}

	public void clickOnHomeButton() {
		homeButton.click();
	}
}
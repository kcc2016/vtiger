package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorInformationPage {
	public VendorInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement vendorInformationText;

	@FindBy(xpath = "//img[@src='themes/softed/images/Home.PNG']")
	private WebElement homeButton;

	public boolean verifyVendorInformationText(String vendorName) {
		return vendorInformationText.getText().contains(vendorName);
	}

	public void clickOnHomeButton() {
		homeButton.click();
	}
}

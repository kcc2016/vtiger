package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {
	public ProductInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='lvtHeaderText' and contains(text(), 'Product Information')]")
	private WebElement productInformationText;

	@FindBy(xpath = "//img[@src='themes/softed/images/Home.PNG']")
	private WebElement homeButton;

	public boolean verifyProductInformationText(String productName) {
		return productInformationText.getText().contains(productName);
	}

	public void clickOnHomeButton() {
		homeButton.click();
	}
}

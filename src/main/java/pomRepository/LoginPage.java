package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement username_text_field;

	@FindBy(name = "user_password")
	private WebElement password_text_field;

	@FindBy(id = "submitButton")
	private WebElement login_button;

	public void login(String username, String password) {
		username_text_field.sendKeys(username);
		password_text_field.sendKeys(password);
		login_button.click();
	}

}

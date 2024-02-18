package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class WebDriverUtility {
	static JavaUtility jUtils = new JavaUtility();

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void implicitWait(WebDriver driver, long seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	public static String takeScreenshot(WebDriver driver, String partialFileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(IPathConstant.SCREENSHOT_FOLDER_PATH + partialFileName + "_"
				+ jUtils.generateSystemDateAndTime() + ".png");
		Files.copy(src, dst);
		return dst.getAbsolutePath();
	}

	public void acceptAlertPopup(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAnotherWindow(WebDriver driver, String partialTitle) {
		Set<String> winIDs = driver.getWindowHandles();
		for (String s : winIDs) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(partialTitle))
				break;
		}
	}
}

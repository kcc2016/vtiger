package practice;

import java.io.File;
import java.io.IOException;

import genericUtility.ExcelUtility;
import genericUtility.FileUtility;
import genericUtility.IPathConstant;
import genericUtility.JavaUtility;

public class Demo1 {
	static FileUtility fUtils = new FileUtility();
	static ExcelUtility eUtils = new ExcelUtility();
	static JavaUtility jUtils = new JavaUtility();

	public static void main(String[] args) throws IOException, InterruptedException {
//		System.out.println(fUtils.fetchDataFromPropertyFile("url"));
//		System.out.println(eUtils.fetchIntegerDataFromExcel("LeadsModule", 1, 5));
//		System.out.println(eUtils.fetchDateDataFromExcel("ProductsModule", 2, 1));
//		System.out.println(eUtils.fetchLocalDateTimeDataFromExcel("ProductsModule", 2, 1));

//		Date date = new Date();
//		System.out.println(date.toString());
//		System.out.println(date.toString().replace(' ', '_').replace(':', '_'));

//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://google.com");
//		WebDriverUtility.takeScreenshot(driver, "screenshot1");
//		Thread.sleep(2000);
//		driver.quit();

//		System.out.println(Calendar.JUNE);

//		LocalDate currentDate = LocalDate.now();
//		System.out.println(currentDate);

		File f = new File(eUtils.fetchStringDataFromExcel(IPathConstant.PRODUCTS_MODULE_SHEET_NAME, 2, 9));
		System.out.println(f.getAbsolutePath());
	}
}

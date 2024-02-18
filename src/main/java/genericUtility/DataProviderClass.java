package genericUtility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
	ExcelUtility eUtils = new ExcelUtility();

	@DataProvider(name = "loginTest")
	public String[][] dp1() throws EncryptedDocumentException, IOException {
		String[][] arr = new String[4][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = eUtils.fetchStringDataFromExcel(IPathConstant.LOGIN_MODULE_SHEET_NAME, i + 1, j);
			}
		}
		return arr;
	}

	@DataProvider(name = "deleteLead")
	public String[][] dp2() throws EncryptedDocumentException, IOException {
		String[][] arr = new String[3][2];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				arr[i][j] = eUtils.fetchStringDataFromExcel(IPathConstant.LEADS_MODULE_SHEET_NAME, i + 6, j);
			}
		}
		return arr;
	}
}

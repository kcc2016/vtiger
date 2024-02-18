package genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyzerImplementationClass implements IRetryAnalyzer {
	int i = 0;
	int limit = 2;

	public boolean retry(ITestResult result) {
		if (i < limit) {
			i++;
			return true;
		}
		return false;
	}

}

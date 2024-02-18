package practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.IRetryAnalyzerImplementationClass;
import genericUtility.ITestListenerImplementationClass;

@Listeners(ITestListenerImplementationClass.class)
public class DemoTest extends BaseClass {
	@Test(retryAnalyzer = IRetryAnalyzerImplementationClass.class)
	public void demoTest() {
		Assert.assertTrue(true);
		System.out.println("demoTest passed");
	}
}

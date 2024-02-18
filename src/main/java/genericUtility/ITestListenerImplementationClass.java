package genericUtility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ITestListenerImplementationClass implements ITestListener {
	ExtentReports report;
	ExtentTest test;
	JavaUtility jUtils = new JavaUtility();
	String extentReportPath;

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName() + " has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName() + " has failed");
		test.log(Status.FAIL, result.getThrowable());
		String pathOfScreenshot = "";
		try {
			pathOfScreenshot = WebDriverUtility.takeScreenshot(BaseClass.sDriver, result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(pathOfScreenshot, "Screenshot of failure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName() + " has been skipped");
	}

	@Override
	public void onStart(ITestContext context) {
		extentReportPath = System.getProperty("user.dir") + "\\ExtentReports\\vtiger_report_"
				+ jUtils.generateSystemDateAndTime() + ".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(extentReportPath);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("My Document");
		spark.config().setReportName("My Report");

		report = new ExtentReports();
		report.attachReporter(spark);

		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();

		File extentReport = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

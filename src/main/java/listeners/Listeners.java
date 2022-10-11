package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReport;

public class Listeners extends Base implements ITestListener {

	public WebDriver driver;
	ExtentReports report = ExtentReport.getreport();
	ExtentTest eTest;
	
	// thread local is used when the tests are running in parallel, because the extent test object is one object
	// so that the is object is share to all the test which is running in parallel.
	ThreadLocal<ExtentTest> extentThreadLocal = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getName();
		eTest = report.createTest(testname );
		extentThreadLocal.set(eTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testname = result.getName();
		//eTest.log(Status.PASS, testname );
		extentThreadLocal.get().log(Status.PASS,testname);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		String testname = result.getName();
		
		//eTest.fail(result.getThrowable());
		extentThreadLocal.get().fail(result.getThrowable()); 

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String screenshotFilePath =takeScreenShot(testname, driver);
		extentThreadLocal.get().addScreenCaptureFromPath(screenshotFilePath,testname);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}

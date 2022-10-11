package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports extentReports;

	public static ExtentReports getreport() {
		String reportPath = System.getProperty("user.dir") + "\\reports\\extentreport.html";

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
		extentSparkReporter.config().setDocumentTitle("Extent Report title");
		extentSparkReporter.config().setReportName("Extent Report");

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		
		extentReports.setSystemInfo("Operating System", "Windows 11");
		extentReports.setSystemInfo("Author", "N BHARATH KUMAR");
		return extentReports;
		

	}

}

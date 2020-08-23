package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class GenerateExtentReport {
	
	
static ExtentReports extent;

	
	public static ExtentReports generateReport() {
	String reportPath = System.getProperty(" user.dir"+ "\\Reports\\testresult.html");
	ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	
	extent = new ExtentReports();																								
	reporter.config().setReportName("Book Store Application");
	reporter.config().setDocumentTitle("BookStore Test Results");
	extent.attachReporter(reporter);
	extent.setSystemInfo("QA", "Bhagyashree Zaware");
	return extent;

	}
}

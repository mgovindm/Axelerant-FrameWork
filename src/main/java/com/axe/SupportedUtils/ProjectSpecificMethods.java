package com.axe.SupportedUtils;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.axe.selenium.CoreUtils.SeleniumBase;

public class ProjectSpecificMethods extends SeleniumBase {

	@BeforeSuite
	public void beforeSuite() throws Exception {
		startReport();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = startApp("chrome", false);
		//driver = startApp("firefox", false);
	}

	@BeforeClass
	public void beforeClass() {
		startTestCase(testSuiteName, testSuiteDescription);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		quit();
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		endResult();
	}

	public void reportUpdate(String testname) {
		// setting Report data
		lib.testName.set(testname);
		svcTest = startTestModule(lib.testName.get());
		svcTest.assignAuthor("Govind");
		svcTest.assignCategory("Smoke");
	}

}

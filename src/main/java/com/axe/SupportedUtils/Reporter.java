package com.axe.SupportedUtils;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public abstract class Reporter{
	
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public String testSuiteName, testSuiteDescription,nodes, authors,category;
	public static ExtentHtmlReporter html;
	public ExtentTest testSuite, test;
	public static ExtentTest svcTest;

	
	public void startReport() {
		reporter = new ExtentHtmlReporter("./reports/report.html");
		reporter.setAppendExisting(false); 
		reporter.config().setChartVisibilityOnOpen(true);
		reporter.config().setDocumentTitle("AxelerantReport");
		reporter.config().setReportName("Test Execution Report");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	public ExtentTest startTestCase(String testSuiteName, String testSuiteDescription) {
		testSuite = extent.createTest(testSuiteName, testSuiteDescription);	
		return testSuite;
	}

	public ExtentTest startTestModule(String nodes) {
		test = testSuite.createNode(nodes);
		return test;
	}

	public void endResult() {
		extent.flush();
	}
    public abstract long takeSnap();
    
    public void reportStep(String dec, String status,boolean bSnap ) {
    	MediaEntityModelProvider img = null;
		if(bSnap && !status.equalsIgnoreCase("INFO")){

			long snapNumber = 100000L;
			snapNumber = takeSnap();
			try {
				img = MediaEntityBuilder.createScreenCaptureFromPath
						("./../reports/images/"+snapNumber+".jpg").build();
			} catch (IOException e) {
				
			}
		}
    	if(status.equalsIgnoreCase("pass")) {
    		test.pass(dec, img);
    	} else if(status.equalsIgnoreCase("fail")) {
    		test.fail(dec, img); 
    	}
    }
    
    public void reportStep(String desc, String status) {
		reportStep(desc, status, false);
	}
    
public static void reportRequest(String desc, String status) {
		
		MediaEntityModelProvider img = null;
		if(status.equalsIgnoreCase("PASS")) {
			svcTest.pass(desc, img);		
		}else if(status.equalsIgnoreCase("FAIL")) {
			svcTest.fail(desc, img);
		}else if(status.equalsIgnoreCase("WARNING")) {
			svcTest.warning(desc, img);		
		}else {
			svcTest.info(desc);
		}	
	}
}















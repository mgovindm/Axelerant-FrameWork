package com.axe.selenium.ui.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.axe.SupportedUtils.JsonComponent;
import com.axe.SupportedUtils.ProjectSpecificMethods;
import com.axe.selenium.pages.HomePage;
import com.axe.selenium.pages.LoginPage;

public class FilterProductBySort extends ProjectSpecificMethods {
	public String strtestdatafilename = "UiTestData";
	JSONObject jsonsuitetestData = JsonComponent.getJsonData("suiteLevelData", strtestdatafilename);

	@BeforeTest
	public void setValues() {
		testSuiteName = "FilterProductBySort";
		testSuiteDescription = "FilterProductBySort-Price,name";
	}

	public void login(String username,String password) {
		LoginPage loginPage = new LoginPage(driver, test);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickOnSubmitSignIn();
	}
	@Test
	public void TC001_verify_user_is_able_to_filterProductByPrice()	{
		String testName = "TC001_verify_user_is_able_to_filterProductByPrice";

		reportUpdate(testName);
		HomePage homePage = new HomePage(driver, test);
		String username=(String) jsonsuitetestData.get("username");
		String password=(String) jsonsuitetestData.get("password");

		try {
			login(username,password);
			homePage.clickOnSummerDresses();
			homePage.selectProductBySort((String) jsonsuitetestData.get("sortByPriceDesc"));
			waitForElementLoad(5000);
		} catch (Exception e) {
			test.log(Status.ERROR, "StackTrace Result: " + e);
			Assert.fail(e.getMessage());
			reportStep("Test Case Not executed Successfully", "fail");
		}
	}
	
	@Test
	public void TC002_verify_user_is_able_to_filterProductByName()	{
		String testName = "TC002_verify_user_is_able_to_filterProductByName";

		reportUpdate(testName);
		HomePage homePage = new HomePage(driver, test);
		String username=(String) jsonsuitetestData.get("username");
		String password=(String) jsonsuitetestData.get("password");

		try {
			login(username,password);
			homePage.clickOnSummerDresses();
			homePage.selectProductBySort((String) jsonsuitetestData.get("sortByProductDesc"));
		} catch (Exception e) {
			test.log(Status.ERROR, "StackTrace Result: " + e);
			Assert.fail(e.getMessage());
			reportStep("Test Case Not executed Successfully", "fail");
		}
	}
	
}


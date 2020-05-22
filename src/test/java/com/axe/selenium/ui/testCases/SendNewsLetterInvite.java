package com.axe.selenium.ui.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.axe.SupportedUtils.JsonComponent;
import com.axe.SupportedUtils.ProjectSpecificMethods;
import com.axe.SupportedUtils.RandomGenerator;
import com.axe.selenium.pages.HomePage;
import com.axe.selenium.pages.LoginPage;

public class SendNewsLetterInvite extends ProjectSpecificMethods {
	public String strtestdatafilename = "UiTestData";
	JSONObject jsonsuitetestData = JsonComponent.getJsonData("suiteLevelData", strtestdatafilename);

	@BeforeTest
	public void setValues() {
		testSuiteName = "SendNewsLetterInvite";
		testSuiteDescription = "Testing the functionality of SendNewsLetterInvite";
	}

	public void login(String username,String password) {
		LoginPage loginPage = new LoginPage(driver, test);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickOnSubmitSignIn();
	}

	@Test
	public void TC001_verify_user_is_able_to_sendNewsLetterInvite()	{
		String testName = "TC001_verify_user_is_able_to_sendNewsLetterInvite";

		reportUpdate(testName);
		HomePage homePage = new HomePage(driver, test);
		String username=(String) jsonsuitetestData.get("username");
		String password=(String) jsonsuitetestData.get("password");

		try {
			login(username,password);
			homePage.enterNewsLetterEmailAddress(RandomGenerator.randomEmailAddress(10));
			homePage.clickOnSendNewsLetterInvite();
			Assert.assertTrue(homePage.validateNewLetterSubscriptionText().contains((String) jsonsuitetestData.get("newsLetterSubscriptionSucessText")));
		} catch (Exception e) {
			test.log(Status.ERROR, "StackTrace Result: " + e);
			Assert.fail(e.getMessage());
			reportStep("Test Case Not executed Successfully", "fail");
		}
	}
}


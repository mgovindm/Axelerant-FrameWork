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
import com.axe.selenium.pages.ProductAddToCartPage;

public class AddProductToCart extends ProjectSpecificMethods {
	public String strtestdatafilename = "UiTestData";
	JSONObject jsonsuitetestData = JsonComponent.getJsonData("suiteLevelData", strtestdatafilename);

	@BeforeTest
	public void setValues() {
		testSuiteName = "Adding Product to Cart";
		testSuiteDescription = "Testing the functionality of adding product to cart";
	}

	public void login(String username,String password) {
		LoginPage loginPage = new LoginPage(driver, test);
		loginPage.enterUserName(username);
		loginPage.enterPassword(password);
		loginPage.clickOnSubmitSignIn();
	}

	@Test
	public void TC001_verify_user_is_able_to_add_product_to_cart_and_process_for_checkOut()	{
		String testName = "TC001_verify_user_is_able_to_add_product_to_cart_and_process_for_checkOut";

		reportUpdate(testName);
		HomePage homePage = new HomePage(driver, test);
		ProductAddToCartPage productAddToCartPage=new ProductAddToCartPage(driver, test);
		String username=(String) jsonsuitetestData.get("username");
		String password=(String) jsonsuitetestData.get("password");

		try {
			login(username,password);
			homePage.clickOnSummerDresses();
			homePage.clickOnAddToCart();
			productAddToCartPage.clickOnProceedToCheckOutToCart();
			productAddToCartPage.clickOnProceedToCheckOutAfterProcess();
			productAddToCartPage.ClickOnProceedTOcheckOutAfterVerifyingAddress();
			productAddToCartPage.clickOnTermAndService();
			productAddToCartPage.clickOnProceedTOcheckOutForShipping();
		} catch (Exception e) {
			test.log(Status.ERROR, "StackTrace Result: " + e);
			Assert.fail(e.getMessage());
			reportStep("Test Case Not executed Successfully", "fail");
		}
	}
	
	/*
	 * Report.html is available under report folder where every step is captured with snap 
	 * AddcartTestNg.xml is available under Suites for execution
	 * By default , test will run in chrome ,if need to run firefox , please comment line number 23 in ProjectSpecificMethods class 
	 * and uncomment line number 24,
	 * In real time, it will be passed from jenkins through parameter
	 */
}


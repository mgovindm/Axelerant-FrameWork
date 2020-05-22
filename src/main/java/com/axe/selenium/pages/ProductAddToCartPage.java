package com.axe.selenium.pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.axe.SupportedUtils.ProjectSpecificMethods;

public class ProductAddToCartPage extends ProjectSpecificMethods{
	public ProductAddToCartPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.XPATH,using="//span[contains(text(),'Proceed to checkout')]")
	private WebElement eleToClickOnProceedTOcheckOut;	
	
	@FindBy(how=How.XPATH,using="//div[@id='center_column']//span[contains(text(),'Proceed to checkout')]")
	private WebElement eleToClickOnProceedTOcheckOutAfterProcess;
	
	@FindBy(how=How.XPATH,using="//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	private WebElement eleToClickOnProceedTOcheckOutAfterVerifyingAddress;
	
	@FindBy(how=How.XPATH,using="//p[contains(text(),'Terms of service')]//following::div[1]")
	private WebElement eleToClickOnTermAndService;
	
	@FindBy(how=How.XPATH,using="//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	private WebElement eleToClickOnProceedTOcheckOutForShipping;
	
	public void clickOnProceedToCheckOutToCart() {
		click(eleToClickOnProceedTOcheckOut);
	}
	
	public void clickOnProceedToCheckOutAfterProcess() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1100)");
		click(eleToClickOnProceedTOcheckOutAfterProcess);
	}
	
	public void ClickOnProceedTOcheckOutAfterVerifyingAddress() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,1100)");
		click(eleToClickOnProceedTOcheckOutAfterVerifyingAddress);
	}
	public void clickOnTermAndService() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,500)");
		 waitForElementLoad(2000);
		click(eleToClickOnTermAndService);
	}
	
	public void clickOnProceedTOcheckOutForShipping() {
		click(eleToClickOnProceedTOcheckOutForShipping);
	}
	
}

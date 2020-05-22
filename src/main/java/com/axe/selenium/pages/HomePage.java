package com.axe.selenium.pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.axe.SupportedUtils.ProjectSpecificMethods;

public class HomePage extends ProjectSpecificMethods{
	public HomePage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.ID,using="newsletter-input")
	private WebElement eleToEnterNewsLetterEmail;	
	
	@FindBy(how=How.XPATH,using="//button[@name='submitNewsletter']")
	private WebElement eleToClickOnSendNewLetterInvite;
	
	@FindBy(how=How.XPATH,using="//p[@class='alert alert-success']")
	private WebElement eleToValidateNewLetterSubScription;
	
	@FindBy(how=How.XPATH,using="//body[@id='my-account']/div[@id='page']/div/header[@id='header']/div/div/div/div[@id='block_top_menu']/ul/li[2]/a[1]")
	private WebElement eleToMouseOverToDresses;
	
	@FindBy(how=How.XPATH,using="//body[@id='my-account']/div[@id='page']/div/header[@id='header']/div/div/div/div[@id='block_top_menu']/ul/li/ul/li[3]/a[1]")
	private WebElement eleToClickOnSummerDresses;
	
	@FindBy(how=How.ID,using="layered_id_attribute_group_1")
	private WebElement eleToClickOnSizeSCheckBox;
	
	@FindBy(how=How.ID,using="selectProductSort")
	private WebElement eleToSelectSortByDropDown;
	
	@FindBy(how=How.XPATH,using="//body[@id='category']/div[@id='page']/div/div[@id='columns']/div/div[@id='center_column']/ul/li[1]/div[1]/div[1]/div[1]/a[1]")
	private WebElement eleToMouseOverToProduct;
	
	
	@FindBy(how=How.XPATH,using="//li[1]//div[1]//div[2]//div[2]//a[1]//span[1]")
	private WebElement eleToClickOnAddToCart;
	
	public void enterNewsLetterEmailAddress(String emailId) {
		clearAndType(eleToEnterNewsLetterEmail, emailId);
	}
	
	public void clickOnSendNewsLetterInvite(){
		click(eleToClickOnSendNewLetterInvite);
	}
	
	public String validateNewLetterSubscriptionText() {
		return getElementText(eleToValidateNewLetterSubScription);
	}
	
	public void clickOnSummerDresses() {
		clickByAction(eleToMouseOverToDresses, eleToClickOnSummerDresses, "Summer Dresses");
	}
	
	public void clickOnSizeSCheckBox() {
		click(eleToClickOnSizeSCheckBox);
	}
	
	public void selectProductBySort(String sortByValue) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,500)");
		 Select sel=new Select(eleToSelectSortByDropDown);
		 sel.selectByValue(sortByValue);
		 reportStep("The value "+sortByValue+" selected", "Pass");
	}
	
	public void clickOnAddToCart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,600)");
		clickByAction(eleToMouseOverToProduct, eleToClickOnAddToCart, "Add Cart");
	}
	
	
}

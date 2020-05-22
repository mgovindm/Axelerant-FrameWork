package com.axe.selenium.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.axe.SupportedUtils.ProjectSpecificMethods;

public class LoginPage extends ProjectSpecificMethods{
	public LoginPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.ID,using="email")
	private WebElement eleToEnterUserName;	
	
	@FindBy(how=How.ID,using="passwd")
	private WebElement eleToEnterPassword;
	
	@FindBy(how=How.ID,using="SubmitLogin")
	private WebElement eleToClickOnSubmitSignIn;
	
	public void enterUserName(String username) {
		clearAndType(eleToEnterUserName, username);
	}
	
	public void enterPassword(String password) {
		clearAndType(eleToEnterPassword, password);
	}	
	public void clickOnSubmitSignIn() {
		click(eleToClickOnSubmitSignIn);
	}
}

package com.axe.selenium.CoreUtils;

import org.openqa.selenium.WebElement;


public interface Element {

	public void click(WebElement ele);
	
	public void clearAndType(WebElement ele,String data);
	
	public String getElementText(WebElement ele);	
	
}





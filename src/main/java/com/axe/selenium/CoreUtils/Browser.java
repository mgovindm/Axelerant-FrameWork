package com.axe.selenium.CoreUtils;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface Browser {
	
	public RemoteWebDriver startApp(String browser,boolean bRemote);
	
	public void close();
	
	public void quit();

	
	
	
	

	
}

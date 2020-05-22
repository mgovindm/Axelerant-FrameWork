package com.axe.selenium.CoreUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axe.SupportedUtils.Constants;
import com.axe.SupportedUtils.Reporter;

public class SeleniumBase extends Reporter implements Browser, Element {

	public RemoteWebDriver driver;
	public WebDriverWait wait;
	public RemoteWebDriver startApp(String browser, boolean bRemote) {
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);

				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
					String Firefoxdriverpath = "./drivers/geckodriver.exe";
					System.setProperty("webdriver.gecko.driver", Firefoxdriverpath);
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					driver = new FirefoxDriver(capabilities);
				} else if (browser.equalsIgnoreCase("ie")) {
					System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
			
			driver.manage().window().maximize();
			driver.navigate().to(Constants.TESTING_APPURL);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return driver;

	}

	@Override
	public void click(WebElement ele) {
		String text = "";
		try {
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep("The Element " + text + " clicked", "pass", true);
		} catch (StaleElementReferenceException e) {
			reportStep("The Element " + text + " could not be clicked", "fail");
			throw new RuntimeException();
		}
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The Data :" + data + " entered Successfully", "pass",true);
		} catch (ElementNotInteractableException e) {
			reportStep("The Element " + ele + " is not Interactable", "fail");
			throw new RuntimeException();
		}

	}

	@Override
	public String getElementText(WebElement ele) {
		String text = ele.getText();
		return text;
	}

	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	@Override
	public void close() {
		driver.close();

	}

	@Override
	public void quit() {
		driver.quit();

	}

	public void clickByAction(WebElement mouseEle, WebElement actionEle, String field){
		try {
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(mouseEle));
			Actions builder = new Actions(driver);
			builder.moveToElement(mouseEle).build().perform();
			waitForElementLoad(2000);
			builder.moveToElement(actionEle).click().build().perform();
			waitForElementLoad(2000);
			reportStep("The Element " + field + " clicked", "pass", false);
		} catch (StaleElementReferenceException e) {
			reportStep("The Element " + field + " Not clicked", "fail");
			throw new RuntimeException();
		}
	}
	
	public void clickBySingleAction(WebElement mouseEle,String field) {
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(mouseEle));
			Actions builder = new Actions(driver);
			builder.moveToElement(mouseEle).click().build().perform();
			reportStep("The Element " + field + " clicked", "pass", false);
		} catch (StaleElementReferenceException e) {
			reportStep("The Element " + field + " Not clicked", "fail");
			throw new RuntimeException();
		}
	}

	public void click(WebElement ele, String Field) {
		String text = "";
		try {
			wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep("The Element " + text + " clicked in " + Field + "", "pass", false);
		} catch (StaleElementReferenceException e) {
			reportStep("The Element " + text + " could not be clicked", "fail");
			throw new RuntimeException();
		}
	}

	public void scrollBottom(WebDriver driver) {
			waitForElementLoad(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,3500)");
	}

	public boolean waitforcomponent(WebDriver driver, WebElement eleofFirstCandidate, long lngWaitTime) {
		WebElement weElement = null;
		for (int i = 0; i <= lngWaitTime && weElement == null; i++) {
			try {
				Thread.sleep(1000);
				weElement = driver.findElement((By) eleofFirstCandidate);
			} catch (Exception e) {
				weElement = null;
			}
		}
		if (weElement == null)
			return false;
		else
			return true;
	}

	
	
	public void waitForElementLoad(int time) {
		try {
			Thread.sleep(time);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

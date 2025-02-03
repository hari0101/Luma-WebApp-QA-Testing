package com.webdriver.DriverUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementUtils implements WebElementUtilsFacade {
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private WebDriver driver;
	private JavascriptExecutor javascript;
	
	public WebElementUtils(WebDriver driver) {
		this.driver = driver;
		this.javascript = (JavascriptExecutor) driver;
	}
	
	//=========================================== METHODS ===========================================
	//click
	@Override
	public void click(WebElement element) {
		element.click();
	}
	
	//Submit the form directly without clicking the button.
	@Override
	public void submit(WebElement element) {
		element.submit();
	}
		
	//Pass URL
	@Override
	public void getURL(String url) {
		driver.get(url);
	}
	
	//maxmize the browser
	@Override
	public void maximize() {
		driver.manage().window().maximize();
	}
	
	//XXXXXXXXXXXXXXXXX URL-BASED-MATHODS XXXXXXXXXXXXXXXXX
	
	@Override
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	
	//XXXXXXXXXXXXXXXXX TEXT-BASED-MATHODS XXXXXXXXXXXXXXXXX 
	//Get the text of the element
	@Override
	public String getText(WebElement element) {
		return element.getText();
	}
	
	//Get the current page title
	@Override
	public String getTitle() {
		return driver.getTitle();
	}
	
	//Get the inputted value of the any attribute in Tag.
	@Override
	public String getDomProperty(WebElement element, String attributeValue) {
		return element.getDomProperty(attributeValue);
	}
	
	//Get the default value of the any attribute in Tag.
	@Override
	public String getDomAttribute(WebElement element, String attributeValue) {
		return element.getDomAttribute(attributeValue);
	}
	
	//XXXXXXXXXXXXXXXXX COOKIES-BASED-METHODS XXXXXXXXXXXXXXXXX 
	@Override
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}
	
	
	//XXXXXXXXXXXXXXXXX CHECK-BASED-METHODS XXXXXXXXXXXXXXXXX 
	//Check whether element is displayed or not.
	@Override
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	//Check whether element is Enabled or not to click.
	@Override
	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}
	
	//Check whether element is Selected or not.
	@Override
	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}
	
	
	//XXXXXXXXXXXXXXXXX PAGE-LOAD / WEBLINK-STATUS XXXXXXXXXXXXXXXXX 
	
	//Measure total time taken to load the page or link
	@Override
	public long measurePageLoadTime() {
		return (long)javascript.executeScript(
				"return (window.performance.timing.loadEventEnd - "
				+ "window.performance.timing.navigationStart);");
	}
	
	@Override
	public Object[] getPageLinkStatusCode(String url) {
		
		if(url.startsWith("https://")) {
			try {
				URL uri = new URL(url);
				HttpURLConnection urlConnect = (HttpURLConnection) uri.openConnection();
				urlConnect.setRequestMethod("GET");
				urlConnect.connect();
				return new Object[] {urlConnect.getResponseCode(), urlConnect.getResponseMessage()};
				
			} catch (IOException e) {
				System.out.println("Unable to find the ResponseCode for the given URL : " + url);
				e.printStackTrace();
			}
		}
		return new Object[] {-1, null};
	}
	
	
}

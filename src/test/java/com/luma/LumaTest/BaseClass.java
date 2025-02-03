package com.luma.LumaTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

import com.webdriver.DriverUtils.*;
import com.webdriver.Webdriverfactory.*;

public class BaseClass {
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	protected static WebDriver driver;
	protected static WebElementUtilsFacade driverUtil;
	protected static JavascriptExecutor javascript;
	
	/**===================================== LOCAL-DRIVER-METHODS ====================================
	 * @param context
	 * @param browserType
	 * 
	 * initializeLocalDriver - helps to initialize the webdriver based on testng.xml requirement.
	 */
	@BeforeTest
	@Parameters({"Browser"})
	public void initializeLocalDriver(ITestContext context, String browserType) {
		Map<String, Object> driverOptions = this.driverOptionMap(context);
		FactoryDriverOptions desiredOptions = DriverOptionsManager.setBrowserdriverOptions(browserType, driverOptions);
		driver = WebDriverManager.getInstance().initLocalWebDriver(browserType, desiredOptions).getLocaldriver();
		context.setAttribute("Driver", driver);
		
		javascript = (JavascriptExecutor) driver;
		driverUtil = new WebElementUtils(driver);
		driverUtil.maximize();
		driverUtil.getURL("https://magento.softwaretestingboard.com/");
		driverUtil.deleteAllCookies();
	}
	
	//Closes all the LocalDriver current instance.
	@BeforeTest(enabled = false)
	public void shutdownLocalDriver() {
		if(driver != null) {
			WebDriverManager.getInstance().quitLocalDriver();
		}
	}
	
	/**
	 * ====================================== REMOTE-DRIVER-METHODS =====================================
	 * @param context
	 * @param browserType
	 * @param os
	 * 
	 * initializeLocalDriver - helps to initialize the Remote webdriver based on testng.xml requirement.
	 */
	@BeforeTest(enabled = false)
	@Parameters({"Browser", "OS"})
	public void initializeRemoteDriver(ITestContext context, String browserType, String os) {
		Map<String, Object> driverOptions = this.driverOptionMap(context);
		FactoryDriverOptions desiredOptions = DriverOptionsManager.setBrowserdriverOptions(browserType, driverOptions);
		DesiredCapabilities remotecap = RemoteWebDriverManager.getInstance().setSeleniumGridCapabilities(os, browserType, desiredOptions);
		driver = RemoteWebDriverManager.getInstance().initRemoteWebDriver("http://localhost:4444", remotecap).getRemoteDriver();
		context.setAttribute("Driver", driver);
		
		driverUtil = new WebElementUtils(driver);
		driverUtil.maximize();
		driverUtil.getURL("https://magento.softwaretestingboard.com/");
	}
	
	//Closes all the RemoteDriver current instance.
	@AfterTest(enabled = false)
	public void shutdownRemoteDriver() {
		if(driver != null) {
			RemoteWebDriverManager.getInstance().quitRemoteDriver();
		}
	}
	
	//Driver options setup method for both LOCAL and REMOTE Drivers.
	private Map<String, Object> driverOptionMap(ITestContext context) {
		//note : DEFAULT BROWSER ARGUMENTS ARE ALREADY SET IN THEIR OWN OPTIONSCLASSES [ex : ChromeOptionsManager]
		
		Map<String, Object> map = new ConcurrentHashMap<>();
		String browserType = context.getCurrentXmlTest().getParameter("Browser");
		
		//SPECIFIC TO THE XML FILE REQUIREDMENT!
		String isHeadless = context.getCurrentXmlTest().getParameter("headless");
		map.put("--headless", Boolean.parseBoolean(isHeadless));
		
		String isGpu = context.getCurrentXmlTest().getParameter("disableGPU");
		map.put("--disable-gpu", Boolean.parseBoolean(isGpu));
		
		String isDisablenotify = context.getCurrentXmlTest().getParameter("disableNotification");
		map.put("--disable-notifications", Boolean.parseBoolean(isDisablenotify));
		
		String isIncognito = context.getCurrentXmlTest().getParameter("incognito");
		if(Boolean.parseBoolean(isIncognito) && browserType.equalsIgnoreCase("chrome")) 
			map.put("--incognito", Boolean.parseBoolean(isIncognito));
		else if(Boolean.parseBoolean(isIncognito) && browserType.equalsIgnoreCase("edge"))
			map.put("--inprivate", Boolean.parseBoolean(isIncognito));
		else if(Boolean.parseBoolean(isIncognito) && browserType.equalsIgnoreCase("firefox"))
			map.put("-private", Boolean.parseBoolean(isIncognito));
		
		String isControlInfo = context.getCurrentXmlTest().getParameter("AutomationControlInfo");
		if(Boolean.parseBoolean(isControlInfo)) 
			map.put("excludeSwitches", new String[] {"enable-automation"});
		
		return map;
	}
	
}

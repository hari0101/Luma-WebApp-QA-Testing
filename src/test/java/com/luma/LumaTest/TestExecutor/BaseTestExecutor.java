package com.luma.LumaTest.TestExecutor;

import org.openqa.selenium.WebDriver;

import com.webdriver.DriverUtils.WebElementUtils;
import com.webdriver.DriverUtils.WebElementUtilsFacade;
import com.webdriver.Interactions.ClickActions;
import com.webdriver.Interactions.ClickActionsFacade;
import com.webdriver.Interactions.KeysActions;
import com.webdriver.Interactions.KeysActionsFacade;

public class BaseTestExecutor {
	//Parent BasicTestExecutor to initialize the Webdriver interactions
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	protected WebElementUtilsFacade elementUtil = null;
	protected ClickActionsFacade clickUtil = null;
	protected KeysActionsFacade keyUtil = null;
	
	public BaseTestExecutor(WebDriver driver) {
		if(elementUtil == null && clickUtil == null && keyUtil == null) {
			elementUtil = new WebElementUtils(driver);
			clickUtil = new ClickActions(driver);
			keyUtil = new KeysActions(driver);
		}
	}
}

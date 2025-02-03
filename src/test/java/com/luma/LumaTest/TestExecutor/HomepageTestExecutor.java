package com.luma.LumaTest.TestExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Consumer;

import com.luma.PageObjectRepository.HomePage;

public class HomepageTestExecutor extends BaseTestExecutor{
	/** Parent BaseTestExecutor
	*common WEBDRIVER instance shared [driver].
	*common element interact instance [ elementUtil ].
	*common key interact instance [ keysUtil ].
	*common click interact instance [ clickUtil ].	
	*/
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	
	private HomePage hpLocators;
	private WebDriver hpDriver;
	
	public HomepageTestExecutor(WebDriver driver) {
		super(driver);
		this.hpDriver = driver;
		hpLocators = new HomePage(driver);
	}
	
	//=========================================== METHODS ===========================================
	
	//Assertion verifying for the continous test chain.
	public HomepageTestExecutor verifyAssert(Consumer<HomepageTestExecutor> Assertion) {
		Assertion.accept(this);
		return this;
	}
	
	//Re-Initialize the all WebElements
	public HomepageTestExecutor refreshHomePageElements() {
		hpLocators.initHomePageElements();
		return this;
	}
	
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX SIGN-IN-METHODS XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	//Get the SignIn page url from the DOM.
	public String getSignInURL() {
		WebElement signIn = hpLocators.getSignInLink();
		return elementUtil.getDomAttribute(signIn, "href");
	}
	
	//Simple homepage check
	public Object[] responseCodeSignInLink() {
		String href = this.getSignInURL();
		return elementUtil.getPageLinkStatusCode(href);
	}
	
	//Clicks the homepage sign in link at Top right of the page.
	public CustomerLoginTestExecutor clickSignInLink() {
		elementUtil.click(hpLocators.getSignInLink());
		return new CustomerLoginTestExecutor(hpDriver);
	}
	
	//XXXXXXXXXXXXXXXXXXXXXX SIGN-IN-SUCCESS XXXXXXXXXXXXXXXXXXXXXX
	
	//Is the greeting is visible
	public Boolean isCustomerGreetDisplayed() {
		return elementUtil.isDisplayed(hpLocators.getCustomerGreetFullname());
	}
	
	//Welcome username if the customer has logged in.
	public String customerGreetDropdown() {
		return elementUtil.getText(hpLocators.getCustomerGreetFullname());
	}
	
	
	
	
}

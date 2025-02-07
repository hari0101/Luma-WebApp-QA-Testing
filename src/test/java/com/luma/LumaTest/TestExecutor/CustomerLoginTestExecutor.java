package com.luma.LumaTest.TestExecutor;

import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;

import com.luma.PageObjectRepository.CustomerLoginPage;
import com.utilities.Datadriven.JsonUtils;

public class CustomerLoginTestExecutor extends BaseTestExecutor {
	/** Parent BaseTestExecutor
	*common WEBDRIVER instance shared [driver].
	*common element interact instance [ elementUtil ].
	*common key interact instance [ keysUtil ].
	*common click interact instance [ clickUtil ].	
	*/
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private CustomerLoginPage clLocators;
	private JsonUtils json;
	
	public CustomerLoginTestExecutor(WebDriver driver) {
		super(driver);
		
		clLocators = new CustomerLoginPage(driver);
		json = new JsonUtils("./src/test/resources/TestCredentialsData.json");
	}
	
	//=========================================== METHODS ===========================================
	//Assertion verifying for the continous test chain.
	public CustomerLoginTestExecutor verifyAssert(Consumer<CustomerLoginTestExecutor> Assertion) {
		Assertion.accept(this);
		return this;
	}
	
	//Get any customerLogin page webelements
	public CustomerLoginPage getCustomerLoginPOMElement(){
		return clLocators;
	}
	
	
	//SignIn URL total loadtime in seconds.
	public int getSignInPageLoadDurationInSeconds() {
		return (int) (elementUtil.measurePageLoadTime() / 1000);
	}
	
	//Enter EmailAddress.
	public CustomerLoginTestExecutor inputSignInEmailAddress(String emailaddress) {
		keyUtil.sendKeys(clLocators.getInputEmailField(), emailaddress);
		return this;
	}
	
	//Clears the EmailAddress from the field
	public CustomerLoginTestExecutor clearSignInEmailAddress() {
		keyUtil.clearInputField(clLocators.getInputEmailField());
		return this;
	}
	
	//Enter Password.
	public CustomerLoginTestExecutor inputSignInPassword(String password) {
		keyUtil.sendKeys(clLocators.getInputPasswordField(), password);
		return this;
	}
	
	//Clears the Password from the field
		public CustomerLoginTestExecutor clearSignInPassword() {
			keyUtil.clearInputField(clLocators.getInputEmailField());
			return this;
		}
	
	
	//Click SignIn button.
	public CustomerLoginTestExecutor clickSignInButton() {
		clickUtil.click(clLocators.getSignInButton());
		return this;
	}
	
	//Incorrect email and password warnings.
	public String getIncorrectSignInMessage() {
		return elementUtil.getText(clLocators.getSignInWarningMessage());
	}
	
	
	//XXXXXXXXXXXXXXXXXXXX JSON-SIGN-IN-DATA XXXXXXXXXXXXXXXXXXXX
	//JSON data for invalid logins
	public String[][] getInvalidSignInJsonData() {
		//Below driven data is not registered in the magento site.
		return json.jsonArrayDataDriven("invalidLoginData", "emailaddress", "password");
	}
	
	//JSON data for invalid logins
	public String[][] getvalidSignInJsonData() {
		//Below driven data is registered in the magento site.
		return json.jsonArrayDataDriven("validLoginData", "emailaddress", "password");
	}

}

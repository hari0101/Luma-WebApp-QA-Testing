package com.luma.LumaTest.TestExecutor;

import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;

import com.luma.PageObjectRepository.CreateNewCustomerAccountPage;
import com.utilities.Datadriven.JsonUtils;

public class CreateNewCustomerAccountTestExecutor extends BaseTestExecutor {
	/** Parent BaseTestExecutor
	*common WEBDRIVER instance shared [driver].
	*common element interact instance [ elementUtil ].
	*common key interact instance [ keysUtil ].
	*common click interact instance [ clickUtil ].	
	*/
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private CreateNewCustomerAccountPage createAccountLocators;
	private JsonUtils json;
	
	public CreateNewCustomerAccountTestExecutor(WebDriver driver) {
		super(driver);
		createAccountLocators = new CreateNewCustomerAccountPage(driver);
		json = new JsonUtils("./src/test/resources/TestCredentialsData.json");
	}
	
	//=========================================== METHODS ==========================================
	//Assertion verifying for the continous test chain.
	public CreateNewCustomerAccountTestExecutor verifyAssert(Consumer<CreateNewCustomerAccountTestExecutor> Assertion) {
		Assertion.accept(this);
		return this;
	}
	
	//Access any webelement of the page Create An Account.
	public CreateNewCustomerAccountPage getCreateAnAccountPOMElements() {
		return createAccountLocators;
	}
	
	
	//XXXXXXXXXXXXXXXXXXXX CREATE-NEW-CUSTOMER-ACCOUNT XXXXXXXXXXXXXXXXXXXXX
	//Input the firstname / Clear firstname field.
	public CreateNewCustomerAccountTestExecutor inputFirstName(String firstname) {
		keyUtil.sendKeys(createAccountLocators.getInputFirstName(), firstname);
		return this;
	}
	
	public CreateNewCustomerAccountTestExecutor clearInputFirstName() {
		keyUtil.clearInputField(createAccountLocators.getInputFirstName());
		return this;
	}
	
	//Input the lastname / Clear lastname field.
	public CreateNewCustomerAccountTestExecutor inputLastName(String lastname) {
		keyUtil.sendKeys(createAccountLocators.getInputLastName(), lastname);
		return this;
	}	
	
	public CreateNewCustomerAccountTestExecutor clearInputLastName() {
		keyUtil.clearInputField(createAccountLocators.getInputLastName());
		return this;
	}
	
	
	//Input the emailaddress / Clear Email Address field.
	public CreateNewCustomerAccountTestExecutor inputEmailAddress(String emailaddress) {
		keyUtil.sendKeys(createAccountLocators.getInputEmailAddress(), emailaddress);
		return this;
	}	
	
	public CreateNewCustomerAccountTestExecutor clearInputEmailAddress() {
		keyUtil.clearInputField(createAccountLocators.getInputEmailAddress());
		return this;
	}
	
	
	//Input the Password / Clear the Password field.
	public CreateNewCustomerAccountTestExecutor inputPassword(String password) {
		keyUtil.sendKeys(createAccountLocators.getInputPassword(), password);
		return this;
	}		
	
	public CreateNewCustomerAccountTestExecutor clearInputPassword() {
		keyUtil.clearInputField(createAccountLocators.getInputPassword());
		return this;
	}
	
	//Input the ConfirmPassword / Clear the ConfirmPassword field.
	public CreateNewCustomerAccountTestExecutor inputConfirmPassword(String confirmpassword) {
		keyUtil.sendKeys(createAccountLocators.getInputConfirmPassword(), confirmpassword);
		return this;
	}		
	
	public CreateNewCustomerAccountTestExecutor clearInputConfirmPassword() {
		keyUtil.clearInputField(createAccountLocators.getInputConfirmPassword());
		return this;
	}
	
	//Removes all the input field values in one shot.
	public CreateNewCustomerAccountTestExecutor clearAllNewCustomerAccountFields() {
		keyUtil.clearInputField(createAccountLocators.getInputFirstName());
		keyUtil.clearInputField(createAccountLocators.getInputLastName());
		keyUtil.clearInputField(createAccountLocators.getInputEmailAddress());
		keyUtil.clearInputField(createAccountLocators.getInputPassword());
		keyUtil.clearInputField(createAccountLocators.getInputConfirmPassword());
		
		return this;
	}
	
	//Click the create an account button.
	public CreateNewCustomerAccountTestExecutor clickCreateAccountButton() {
		clickUtil.click(createAccountLocators.getButtonCreateAccount());
		return this;
	}
	
	//SignIn URL total loadtime in seconds.
	public int get_Create_New_Customer_Account_PageLoadDuration_InSeconds() {
		return (int) (elementUtil.measurePageLoadTime() / 1000);
	}
	
	//XXXXXXXXXXXXXXXXXXXX JSON-DATA XXXXXXXXXXXXXXXXXXXXX
	//./src/test/resources/TestCredential.json
	//With json data we get the already register data.
	public String[][] getRegisterCustomerAccountData() {
		return json.jsonArrayDataDriven("registeredAccountData", "firstname", "lastname", "emailaddress", "password", "confirmpassword");
		
	}
	
	//Json data which is not register in the website 
	public String[][] getnewCustomerAccountData() {
		return json.jsonArrayDataDriven("NonRegisterAccountData", "firstname", "lastname", "emailaddress", "password", "confirmpassword");
		
	}
}

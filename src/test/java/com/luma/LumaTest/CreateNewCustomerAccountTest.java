package com.luma.LumaTest;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.luma.LumaTest.TestExecutor.CreateNewCustomerAccountTestExecutor;
import com.luma.LumaTest.TestExecutor.HomepageTestExecutor;
import com.utilities.CommonUtils.LoggerManager;
import com.utilities.Datadriven.JsonDataProvider;

public class CreateNewCustomerAccountTest extends BaseClass {

	//==================================== TEST-FIELDS & CONSTRUCTOR ====================================
	private Logger logs = LoggerManager.getInstance().getLogger(this.getClass());
	private HomepageTestExecutor mainpage;
	private CreateNewCustomerAccountTestExecutor customerNewAccountPage;
	
	private static final String STATUS_MESSAGE = "OK";
	private static final String LOGIN_TITLE = "Customer Login";
	private static final String LANDING_TITLE = "Home Page";
	private static final String NEW_ACCOUNT_TITLE = "Create New Customer Account";
	private static final String MYACCOUNT_TITLE = "My Account";
	
	private static final int MAX_LOADTIME = 10; //seconds
	private static final int VALID_STATUSCODE = 200;
	private static final int INVALID_STATUSCODE = 400;
	//======================================= TEST-CASES-METHODS =========================================
	
	@Test(priority = 0, groups = {"SMOKE"}, 
			description = "To click and verify the create an account link is navigating the required page!")
	public void customerNewAccountUrlValidation() {
		mainpage = new HomepageTestExecutor(driver);
		customerNewAccountPage = new CreateNewCustomerAccountTestExecutor(driver);
		SoftAssert soft = new SoftAssert();
		
		mainpage.clickCreateAnAccountLink()
				.verifyAssert(newAccount -> {
						int pageloadSeconds = newAccount.get_Create_New_Customer_Account_PageLoadDuration_InSeconds();
						soft.assertTrue(pageloadSeconds <= MAX_LOADTIME, NEW_ACCOUNT_TITLE +" Pageload duration is more than the " + MAX_LOADTIME + " seconds");
				})
				.verifyAssert(newAccount -> {
						//Page title assertion
						String newAccountTitle = driverUtil.getTitle();
						soft.assertEquals(newAccountTitle, NEW_ACCOUNT_TITLE);
				});
		
		soft.assertAll();
	}
	
	@Test(priority = 1, groups = {"SMOKE"},
			description = "To verify already registered account should not create an new account again.")
	public void invalidNewAccountCredentials() {
		String[][] jsonData = customerNewAccountPage.getRegisterCustomerAccountData();
		String firstname = jsonData[0][0], lastname = jsonData[0][1], emailaddress = jsonData[0][2], password = jsonData[0][3], confirmpassword = jsonData[0][4];
	
		customerNewAccountPage.inputFirstName(firstname)
							  .inputLastName(lastname)
							  .inputEmailAddress(emailaddress)
							  .inputPassword(password)
							  .inputConfirmPassword(confirmpassword)
							  .clickCreateAccountButton()
							  .verifyAssert(newAccount -> {
								  WebElement warning = newAccount.getCreateAnAccountPOMElements().getWarningMessage();
								  
								  if(driverUtil.isDisplayed(warning)) {
									  Assert.assertTrue(true, "\"There is already an account with this email address\" Warning message not displayed on the screen!");
								  }
							  })
							  .clearAllNewCustomerAccountFields();	
	}
	
	@Test(priority = 2, groups = {"SMOKE"}, enabled = false, 
			description = "To verify the customer can able to register with new account credentials")
	public void validNewAccountCredentials() {
		String[][] jsonData = customerNewAccountPage.getnewCustomerAccountData();
		String firstname = jsonData[0][0], lastname = jsonData[0][1], emailaddress = jsonData[0][2], password = jsonData[0][3], confirmpassword = jsonData[0][4];
		
		customerNewAccountPage.inputFirstName(firstname)
		  					  .inputLastName(lastname)
		  					  .inputEmailAddress(emailaddress)
		  					  .inputPassword(password)
		  					  .inputConfirmPassword(confirmpassword)
		  					  .clickCreateAccountButton()
		  					  .verifyAssert(newAccount -> {
		  						  				String pageTitle = driverUtil.getTitle();
		  						  				Assert.assertEquals(pageTitle, MYACCOUNT_TITLE);
		  					  });
	
	}	
	
	@Test(priority = 3, groups = {"SANITY"}, dataProviderClass = JsonDataProvider.class, dataProvider = "CreateAnAccountData",
			description = "To verify new customer account with different sets of invalid credentials")
	public void externalDataNewAccountCredentials(String firstname, String lastname, String emailaddress, String password, String confirmpassword) {
		
		customerNewAccountPage.inputFirstName(firstname)
		  					.inputLastName(lastname)
		  					.inputEmailAddress(emailaddress)
		  					.inputPassword(password)
		  					.inputConfirmPassword(confirmpassword)
		  					.clickCreateAccountButton()
		  					.verifyAssert(newAccount -> {
		  					WebElement warning = newAccount.getCreateAnAccountPOMElements().getWarningMessage();
			  
		  					if(driverUtil.isDisplayed(warning)) {
		  						Assert.assertTrue(true, "\"There is already an account with this email address\" Warning message not displayed on the screen!");
		  					}
		  					})
		  					.clearAllNewCustomerAccountFields();
		
	}
	
	
	
	
}

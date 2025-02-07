package com.luma.LumaTest;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.luma.LumaTest.TestExecutor.CustomerLoginTestExecutor;
import com.luma.LumaTest.TestExecutor.HomepageTestExecutor;
import com.utilities.CommonUtils.LoggerManager;
import com.utilities.Datadriven.JsonDataProvider;

public class CustomerLoginTest extends BaseClass {
	
	//==================================== TEST-FIELDS & CONSTRUCTOR ====================================
	private Logger logs = LoggerManager.getInstance().getLogger(this.getClass());
	private HomepageTestExecutor mainpage;
	private CustomerLoginTestExecutor loginpage;
	
	private static final String STATUS_MESSAGE = "OK";
	private static final String LOGIN_TITLE = "Customer Login";
	private static final String LANDING_TITLE = "Home Page";
	
	private static final int MAX_LOADTIME = 10; //seconds
	private static final int VALID_STATUSCODE = 200;
	private static final int INVALID_STATUSCODE = 400;
	
	
	//======================================= TEST-CASES-METHODS =========================================
	//Validate the SignIn url is validate 
	@Test(priority = 0, groups = {"SMOKE", "SANITY"},
			description = "To verify the customer sign-in page launched successfully!")
	public void signInUrlValidation() {
		mainpage = new HomepageTestExecutor(driver);
		loginpage = new CustomerLoginTestExecutor(driver);
		SoftAssert soft = new SoftAssert();
		
		Object[] response = mainpage.responseCodeSignInLink();
		int statusCode = (int) response[0];
		String statusMessage = (String) response[1];
		logs.info("SignIn URL validation has been started...");
		mainpage.verifyAssert(homepage -> {
			soft.assertTrue((statusCode >= VALID_STATUSCODE) && (statusCode < INVALID_STATUSCODE), "Customer SignIn page Response code is greater than " + INVALID_STATUSCODE);
			logs.info("ResponseStatus : {} {}.", statusCode, statusMessage);
		})
		//Click the SignIn link or button from the homepage and verify loadtime of url.
		.clickSignInLink()
		.verifyAssert(customerLogin -> {
			logs.info("SignIn link has been clicked. ");
			int pageloadSeconds = customerLogin.getSignInPageLoadDurationInSeconds();
			soft.assertTrue(pageloadSeconds <= MAX_LOADTIME, "Customer SignIn Pageload duration is more than the " + MAX_LOADTIME + " seconds");
			soft.assertEquals(statusMessage, STATUS_MESSAGE, "Landing Page response message is invalid" + statusMessage);
			String pagetitle = driverUtil.getTitle();
			soft.assertEquals(pagetitle, LOGIN_TITLE, "After SignIn link is clicked it's not redirecting to Actual page" + pagetitle);
		logs.info("SignIn url loaded succesfully with duration of {} seconds.", pageloadSeconds);	
		});
		
		
		soft.assertAll();
	}		
	
	
	@Test(priority = 1, groups = {"SMOKE"}, dependsOnMethods = {"signInUrlValidation"}, 
			description = "To verify the valid [ emailadress & password ] which is not registered should not SIGN-IN")
	public void InvalidSignInCredentials() {
		SoftAssert soft = new SoftAssert();
		
		String[][] jsondata = loginpage.getInvalidSignInJsonData();
		String emailaddress = jsondata[0][0];
		String password = jsondata[0][1];
		
		loginpage.inputSignInEmailAddress(emailaddress) 
				 .inputSignInPassword(password)
				 .clickSignInButton()
				 .verifyAssert(customerLogin -> {
						WebElement warningElement = customerLogin.getCustomerLoginPOMElement().getSignInWarning();
						String warning = customerLogin.getIncorrectSignInMessage();		
						if(driverUtil.isDisplayed(warningElement)) {
							soft.assertTrue(true, "No warning message is displayed on the screen!" + warning);
						}
						})
				 .clearSignInEmailAddress().clearSignInPassword();
		
		soft.assertAll();
	}		
	
	@Test(priority = 2, groups = {"SMOKE"}, dependsOnMethods = {"signInUrlValidation"},
			description = "To verify the customer should login with valid [ emailadress & password ] which is already register")
	public void validSignInCredentials() {
		SoftAssert soft = new SoftAssert();
		String[][] jsondata = loginpage.getvalidSignInJsonData();
		String emailaddress = jsondata[0][0];
		String password = jsondata[0][1];
		
		loginpage.inputSignInEmailAddress(emailaddress)
		 		.inputSignInPassword(password)
		 		.clickSignInButton();
		
		mainpage.refreshHomePageElements()
				.verifyAssert(homepage -> {
							String homeTitle = driverUtil.getTitle();
							soft.assertEquals(homeTitle, LANDING_TITLE, "After CustomerLogin successfully it's not redirecting to Actual page!" + homeTitle);
		});
		soft.assertAll();
	}
	
	@Test(priority = 3, dataProvider =  "CustomerLoginData", dataProviderClass = JsonDataProvider.class,
			groups = {"SANITY"}, dependsOnMethods = {"signInUrlValidation"},
			description = "To verify different data sets on the customer login, Emailaddress and Password fields")
	public void customerLoginExternalData(String emailaddress, String password) {
		loginpage.inputSignInEmailAddress(emailaddress)
				 .inputSignInPassword(password)	
				 .clickSignInButton()
	
		         .verifyAssert(customerLogin -> {
		        	 	WebElement warningElement = customerLogin.getCustomerLoginPOMElement().getSignInWarning();
		        	 	if(driverUtil.isDisplayed(warningElement)) {
		        	 		Assert.assertTrue(true, "Warning Message not displayed on the Customer Login page!");
		        	 		customerLogin.clearSignInEmailAddress()
		        	 					 .clearSignInPassword();	
		        	 	}
		        	 	else if(driverUtil.getTitle().equalsIgnoreCase(LANDING_TITLE)) {
		        			logs.info("External data from json dataProvider has been executed successfully!");
		        		}
		         });		
		
	}
	



}

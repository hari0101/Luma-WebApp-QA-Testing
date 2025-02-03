package com.luma.LumaTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.luma.LumaTest.TestExecutor.CustomerLoginTestExecutor;
import com.luma.LumaTest.TestExecutor.HomepageTestExecutor;

public class CustomerLoginTest extends BaseClass {
	
	//==================================== TEST-FIELDS & CONSTRUCTOR ====================================
	private static final int MAX_LOADTIME = 10; //seconds
	private static final int VALID_STATUSCODE = 200;
	private static final int INVALID_STATUSCODE = 400;
	private HomepageTestExecutor mainpage;
	private CustomerLoginTestExecutor loginpage;
	
	//======================================= TEST-CASES-METHODS =========================================
	//Validate the SignIn url is validate 
	@Test(priority = 0, groups = {"SMOKE_TEST"},
			description = "To verify the customer sign-in page launched successfully!")
	public void signInUrlValidation() {
		mainpage = new HomepageTestExecutor(driver);
		SoftAssert soft = new SoftAssert();
		Object[] response = mainpage.responseCodeSignInLink();
		int statusCode = (int) response[0];
		String statusMessage = (String) response[1];
		
		mainpage.verifyAssert(homepage -> {
			soft.assertTrue((statusCode >= VALID_STATUSCODE) && (statusCode < INVALID_STATUSCODE), "Customer SignIn page Response code is greater than " + INVALID_STATUSCODE);
			System.out.printf("%sSIGN-IN-LINK-VALIDATION%s%n [ URL : %s%n   RESPONSE CODE : %d %s%n", 
								"-".repeat(20),"-".repeat(20),homepage.getSignInURL(), statusCode, statusMessage);
		})
		//Click the SignIn link or button from the homepage and verify loadtime of url.
		.clickSignInLink()
		.verifyAssert(customerLogin -> {
			int pageloadSeconds = customerLogin.getSignInPageLoadDurationInSeconds();
			soft.assertTrue(pageloadSeconds <= MAX_LOADTIME, "Customer SignIn Pageload duration is more than the " + MAX_LOADTIME + " seconds");
			System.out.printf("   PAGE LOAD DURATION : %d seconds. ] %n%n", pageloadSeconds);
		});
		
		soft.assertAll();
	}		
	
	
	@Test(priority = 1, groups = {"SMOKE_TEST"}, dependsOnMethods = {"signInUrlValidation"}, 
			description = "To verify the valid [ emailadress & password ] which is not registered should not SIGN-IN")
	public void InvalidSignInCredentials() {
		loginpage = new CustomerLoginTestExecutor(driver);
		SoftAssert soft = new SoftAssert();
		
		String[][] jsondata = loginpage.getInvalidSignInJsonData();
		String emailaddress = jsondata[0][0];
		String password = jsondata[0][1];
		
		loginpage.inputSignInEmailAddress(emailaddress)
				 .inputSignInPassword(password)
				 .clickSignInButton()
				 .verifyAssert(customerLogin -> {
						WebElement warnElement = customerLogin.getCustomerLoginWebElement().getSignInWarning();
						String warning = customerLogin.getIncorrectSignInMessage();		
						if(driverUtil.isDisplayed(warnElement)) {
							soft.assertTrue(true, "No warning message is displayed on the screen!" + warning);
							System.out.printf("WARNING MESSAGE : \"%s\" %n Warning Message has been displayed Successfully!%n%n" , warning);
						}
						})
				 .clearSignInEmailAddress().clearSignInPassword();
		
		soft.assertAll();
	}		
	
	@Test(priority = 2, groups = {"SMOKE_TEST"}, dependsOnMethods = {"signInUrlValidation"},
			description = "To verify the customer should login with valid [ emailadress & password ] which is already register")
	public void validSignInCredentials() {
		SoftAssert soft = new SoftAssert();
		String[][] jsondata = loginpage.getvalidSignInJsonData();
		String emailaddress = jsondata[0][0];
		String password = jsondata[0][1];
		String fullname	= jsondata[0][2];
		
		loginpage.inputSignInEmailAddress(emailaddress)
		 		.inputSignInPassword(password)
		 		.clickSignInButton();
		
		mainpage.refreshHomePageElements()
				.verifyAssert(homepage -> {
							if(homepage.isCustomerGreetDisplayed()) {
								String username = homepage.customerGreetDropdown();
								soft.assertEquals(fullname, username.contains(fullname), "Registered Customer Firstname and Lastname is not matching!");
								System.out.println("WELCOME GREET: " + username);
							}
		});
		
	}





}

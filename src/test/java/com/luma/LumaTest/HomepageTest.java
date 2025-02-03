package com.luma.LumaTest;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.luma.LumaTest.TestExecutor.HomepageTestExecutor;

public class HomepageTest extends BaseClass {
	
	//==================================== TEST-FIELDS & CONSTRUCTOR ====================================
	private HomepageTestExecutor mainpage;
	private static final int MAX_LOADTIME = 10; //seconds
	private static final int VALID_STATUSCODE = 200;
	private static final int INVALID_STATUSCODE = 400;
	//======================================= TEST-CASES-METHODS =========================================
	
	@Test(priority = 0, groups = {"SMOKE_TEST"}, description = "To verify the landing page of the website should be as requirement!")
	public void basePageUrlValidation() {
		mainpage = new HomepageTestExecutor(driver);
		SoftAssert soft = new SoftAssert();
		
		String baseURL = driverUtil.getCurrentURL();
		Object[] response = driverUtil.getPageLinkStatusCode(baseURL);	
		int statuscode = (int) response[0];
		String statusMessage = (String)response[1];
		int pageloadSeconds = (int) driverUtil.measurePageLoadTime() / 1000;
		
		mainpage.verifyAssert(homepage -> {
				soft.assertTrue((statuscode >= VALID_STATUSCODE && statuscode < INVALID_STATUSCODE), "HomePage Response code is greater than " + INVALID_STATUSCODE);
				soft.assertTrue(pageloadSeconds <= MAX_LOADTIME , "BasePage Pageload duration is more than the " + MAX_LOADTIME + " seconds");
				System.out.printf("%sBASEPAGE-LINK-VALIDATION%s%n [ URL : %s%n   RESPONSE CODE : %d %s%n", 
						"-".repeat(20),"-".repeat(20), baseURL, statuscode, statusMessage);
				System.out.printf("   TOTAL PAGELOAD DURATION : %d seconds. ] %n", pageloadSeconds);
		});
		
		soft.assertAll();
	}
	
	
	
}

package com.luma.LumaTest;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.luma.LumaTest.TestExecutor.HomepageTestExecutor;
import com.utilities.CommonUtils.LoggerManager;

public class HomepageTest extends BaseClass {
	
	//==================================== TEST-FIELDS & CONSTRUCTOR ====================================
	private Logger logs = LoggerManager.getInstance().getLogger(this.getClass());
	private HomepageTestExecutor mainpage;
	
	private static final String STATUS_MESSAGE = "OK";
	
	private static final int MAX_LOADTIME = 10; //seconds
	private static final int VALID_STATUSCODE = 200;
	private static final int INVALID_STATUSCODE = 400;
	
	//======================================= TEST-CASES-METHODS =========================================
	
	@Test(priority = 0, groups = {"SMOKE", "SANITY"}, description = "To verify the landing page of the website should be as requirement!")
	public void basePageUrlValidation() {
		mainpage = new HomepageTestExecutor(driver);
		SoftAssert soft = new SoftAssert();
		
		String baseURL = driverUtil.getCurrentURL();
		logs.info("Landing Page url loading in the browser! URL : " + baseURL);
		Object[] response = driverUtil.getPageLinkStatusCode(baseURL);	
		int statuscode = (int) response[0];
		String statusMessage = (String)response[1];
		int pageloadSeconds = (int) driverUtil.measurePageLoadTime() / 1000;
		
		mainpage.verifyAssert(homepage -> {
				soft.assertTrue((statuscode >= VALID_STATUSCODE && statuscode < INVALID_STATUSCODE), "Landing Page Response code is greater than " + INVALID_STATUSCODE);
				soft.assertTrue(pageloadSeconds <= MAX_LOADTIME , "Landing Page Pageload duration is "+ pageloadSeconds+ " seconds, Which is more than the " + MAX_LOADTIME + " seconds");
				soft.assertEquals(statusMessage, STATUS_MESSAGE, "Landing Page response message is invalid" + statusMessage);
		});
		
		logs.info("Landing Page successfully loaded in the browser with {} seconds and ResponseStatus {} {}", pageloadSeconds, statuscode, statusMessage);
		soft.assertAll();
	}
	
	
	
}

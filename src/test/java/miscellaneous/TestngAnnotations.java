package miscellaneous;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestngAnnotations {
	
	
	@Test(priority = 0)
	public void signInUrlTest() {
		
		SoftAssert soft = new SoftAssert();
		soft.assertEquals("Url", "Url");
		System.out.println("URL HEALTH CHECK");
		
		soft.assertAll();
	}
	
	@Test(priority = 1)
	public void invalidSignInTest() {
		SoftAssert soft = new SoftAssert();
		int loadtime = 11;
		int maxloadtime = 10;
		soft.assertTrue( loadtime <= maxloadtime, "Invalid PageLoadTime is should not greater than " + maxloadtime);
		System.out.println("No login with valid credentials without registered");
		soft.assertAll();
	}
	
	@Test(priority = 2)
	public void validSignInTest() {
		SoftAssert soft = new SoftAssert();
		int statuscode = 100;
		int validCode = 200;
		soft.assertTrue( validCode == statuscode, "Invalid ResponseCode Error page! " + statuscode);
		System.out.println("Login successful with valid credentials which is registered.");
		
		soft.assertAll();
	}
	
	
}

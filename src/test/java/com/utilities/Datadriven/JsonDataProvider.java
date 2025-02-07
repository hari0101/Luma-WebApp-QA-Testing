package com.utilities.Datadriven;

import org.testng.annotations.DataProvider;

public class JsonDataProvider {

	//==================================== FIELDS & CONSTRUCTOR ====================================
	private JsonUtils util;
	private static String jsonpath = "./src/test/resources/dataprovider.json"; 
	
	public JsonDataProvider() {
		util = new JsonUtils(jsonpath);
	}
	//=========================================== METHODS ===========================================
	
	@DataProvider(name = "CustomerLoginData")
	public String[][] validLoginTestData() {
		return util.jsonArrayDataDriven("customerlogintest", "emailaddress", "password");
	}
	
	@DataProvider(name = "CreateAnAccountData")
	public String[][] invalidNewAccountCredentials() {
		return util.jsonArrayDataDriven("NewAccountRegisterTest", "firstname", "lastname", "emailaddress", "password", "confirmpassword");
	}
	
}

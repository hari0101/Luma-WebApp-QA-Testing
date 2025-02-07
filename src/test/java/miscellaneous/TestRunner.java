package miscellaneous;

import org.testng.annotations.Test;

import com.utilities.Datadriven.JsonDataProvider;

public class TestRunner {

	@Test(dataProvider =  "CustomerLoginData", dataProviderClass = JsonDataProvider.class)
	public void printDataTest(String emailaddress, String password) {
		int counter = 1;
		
		System.out.println(counter + "." + emailaddress);
		System.out.println(counter + "." + password);
		counter++;
	}

}

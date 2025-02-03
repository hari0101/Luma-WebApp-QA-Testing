package miscellaneous;

import com.utilities.Datadriven.JsonUtils;

public class ExampleData {

	public static void main(String[] args) {
		
		String jsonPath = "./src/test/resources/TestCredentialsData.json";
		JsonUtils json = new JsonUtils(jsonPath);
		
		String[][] data = json.jsonArrayDataDriven("validNewAccountData", "firstname", "lastname", "emailaddress", "password", "confirmpassword");
	
		System.out.println(data[0][0]);
		System.out.println(data[0][1]);
		System.out.println(data[0][2]);
		System.out.println(data[0][3]);
		System.out.println(data[0][4]);
	}

}

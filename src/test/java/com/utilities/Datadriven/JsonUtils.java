package com.utilities.Datadriven;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * JsonUtils.class helps to achieve the data-driven testing from the JSON file.
 */
public class JsonUtils {
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private ObjectMapper jsonmapper;
	private JsonNode rootnode;
	
	public JsonUtils(String pathname) {
		jsonmapper = new ObjectMapper();
		try {
			rootnode = jsonmapper.readTree(new File(pathname));
		} 
		catch (IOException io) {
			System.out.println("Unable to read the JSON file : " + pathname);
			io.printStackTrace();
		}
	}
	
	//=========================================== METHODS ===========================================
	
	public String[][] jsonArrayDataDriven(String keyArrayValue, String keyValueFirst, String keyValueSecond) {
		/**
		 * @param keyArrayValue
		 * @param keyValueFirst
		 * @param keyValueSecond
		 * 
		 * jsonDataDriven get the data from the json file and act as testdata source.
		 * 
		 * This method only allows the json file with one array with all the elements
		 * are objects with only two key-value pairs.
		 * 
		 * @return String[][] jsondata
		 */	
		JsonNode keyArray = rootnode.get(keyArrayValue);
		JsonNode firstObject = keyArray.get(0);
		
		String[][] jsondata = new String[keyArray.size()][firstObject.size()];
		
		for(int i = 0; i < keyArray.size(); i++) {
			JsonNode obj = keyArray.get(i);
			String user = obj.get(keyValueFirst).asText();
			String pwd = obj.get(keyValueSecond).asText();
			
			for(int j = 0; j < obj.size(); j++) {
				if(j == 0)
					jsondata[i][j] = user;
				
				if(j == 1)
					jsondata[i][j] = pwd;
			}
		}
		return jsondata;
	}
	
	//Three data json array
	public String[][] jsonArrayDataDriven(String keyArrayValue, String keyValueFirst, String keyValueSecond, String keyValueThird) {
		JsonNode keyArray = rootnode.get(keyArrayValue);
		JsonNode firstObject = keyArray.get(0);
		
		String[][] jsondata = new String[keyArray.size()][firstObject.size()];
		
		for(int i = 0; i < keyArray.size(); i++) {
			JsonNode obj = keyArray.get(i);
			String email = obj.get(keyValueFirst).asText();
			String passwd = obj.get(keyValueSecond).asText();
			String fullname = obj.get(keyValueThird).asText();
			
			for(int j = 0; j < obj.size(); j++) {
				if(j == 0)
					jsondata[i][j] = email;
				
				if(j == 1)
					jsondata[i][j] = passwd;
				
				if(j == 2)
					jsondata[i][j] = fullname;
			}
		}
		return jsondata;
	}
	
	//Five different data of json array
	
	public String[][] jsonArrayDataDriven(String keyArrayValue, String keyValueFirst, String keyValueSecond, String keyValueThird, String keyValueFourth, String keyValueFivth) {
		JsonNode keyArray = rootnode.get(keyArrayValue);
		JsonNode firstObject = keyArray.get(0);
		
		String[][] jsondata = new String[keyArray.size()][firstObject.size()];
		
		for(int i = 0; i < keyArray.size(); i++) {
			JsonNode obj = keyArray.get(i);
			String firstname = obj.get(keyValueFirst).asText();
			String lastname = obj.get(keyValueSecond).asText();
			String email = obj.get(keyValueThird).asText();
			String passwd = obj.get(keyValueFourth).asText();
			String confirmPwd= obj.get(keyValueFivth).asText();
			
			for(int j = 0; j < obj.size(); j++) {
				if(j == 0)
					jsondata[i][j] = firstname;
				
				if(j == 1)
					jsondata[i][j] = lastname;
				
				if(j == 2)
					jsondata[i][j] = email;
				
				if(j == 3)
					jsondata[i][j] = passwd;
				
				if(j == 4)
					jsondata[i][j] = confirmPwd;
			}
		}
		return jsondata;
	}
	
	public int getJsonArraySize(String arrayName) {
		return rootnode.get(arrayName).size();
	}
	
	
}

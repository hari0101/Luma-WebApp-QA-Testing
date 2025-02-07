package com.luma.PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.Getter;

@Getter
public class CreateNewCustomerAccountPage {

	//==================================== FIELDS & CONSTRUCTOR ====================================
	private WebDriver pagedriver;
		
	public CreateNewCustomerAccountPage(WebDriver driver) {
		this.pagedriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "firstname")
	private WebElement inputFirstName;
	
	@FindBy(id ="lastname")
	private WebElement inputLastName;
	
	@FindBy(id ="email_address")
	private WebElement inputEmailAddress;
	
	@FindBy(id ="password")
	private WebElement inputPassword;
	
	@FindBy(name ="password_confirmation")
	private WebElement inputConfirmPassword;
	
	@FindBy(css ="button[title='Create an Account']")
	private WebElement buttonCreateAccount;
	
	@FindBy(css = ".page.messages")
	private WebElement warningMessage;

	//============================================ METHODS ============================================
		
	public void initCreateNewCustomerAccountElements() {
		if(this.pagedriver != null) {
			PageFactory.initElements(this.pagedriver, this);
		}
	}	
		

}

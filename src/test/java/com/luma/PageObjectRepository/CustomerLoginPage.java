package com.luma.PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import lombok.Getter;

@Getter
public class CustomerLoginPage {
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private WebDriver pagedriver;
		
	public CustomerLoginPage(WebDriver driver) {
		this.pagedriver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(id = "email")
	private WebElement inputEmailField;
	
	@FindBy(id = "pass")
	private WebElement inputPasswordField;
	
	@FindBy(id = "send2")
	private WebElement signInButton;
	
	@FindBy(css = ".messages:last-child")
	private WebElement signInWarning;
	
	@FindBy(css = "div[data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	private WebElement signInWarningMessage;
	
	
	
	//============================================ METHODS ============================================
	
	public void initCustomerLoginPageElements() {
		if(this.pagedriver != null) {
			PageFactory.initElements(this.pagedriver, this);
		}
	}
}

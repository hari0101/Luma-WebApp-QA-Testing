package com.luma.PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import lombok.Getter;

@Getter
public class HomePage {
		
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private WebDriver pagedriver;
	
	
	public HomePage(WebDriver driver) {
		this.pagedriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div[class='panel header'] li[data-label='or'] a")
	private WebElement signInLink;	
	
	@FindBy(css = ".greet > span")
	private WebElement customerGreetFullname;
	
	@FindBy(css = "header[class='page-header'] li:nth-child(3) a:nth-child(1)")
	private WebElement createAnAccountLink;
	//============================================ METHODS ============================================
	
	public void initHomePageElements() {
		if(this.pagedriver != null) {
			PageFactory.initElements(this.pagedriver, this);
		}
	}	
	

}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	//constructor
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	
	// Locators
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login_btn;
	
	
	//action methods
	 public void setEmailString(String email) {
		 txt_email.sendKeys(email);
	 }
	 
	 public void setPasswordString(String password) {
		 txt_password.sendKeys(password);
	 }
	 
	 public void clickLogin() {
		 login_btn.click();
	 }
}
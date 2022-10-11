package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver =null;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id ="input-email")
	private WebElement username;
	
	@FindBy(id = "input-password")
	 private WebElement password;
	
	@FindBy(xpath = "//*[@value='Login']")
	private WebElement loginbtn;
	
	@FindBy(linkText = "Edit your account information")
	private WebElement editAccountInfo;
	
	public WebElement enterUsername() {
		//username.sendKeys(uname);
		return username;
	}
	
	public WebElement enterPassword() {
		//password.sendKeys(psw);
		return password;
	}
	
	public WebElement clickOnloginbtn() {
		return loginbtn;
	}
	
	public WebElement editAccountInformation() {
		return editAccountInfo;
	}
	

}

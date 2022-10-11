package stepdefinations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginStepDef extends Base{
	
	WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	
	@Given("Open any Browser")
	public void open_any_browser() {
	 driver= initializeBrowser();
	}

	@Given("Navigate to Login page")
	public void navigate_to_login_page() {
	driver.get(properties.getProperty("url"));
	landingPage = new LandingPage(driver);
	landingPage.getMyAccount();
	landingPage.getLogin();
	}

	@When("User enters username as {string} and password as {string} into the fields")
	public void user_enters_username_as_and_password_as_into_the_fields(String username, String password) {
		loginPage = new LoginPage(driver);
		loginPage.enterUsername().sendKeys(username);
		loginPage.enterPassword().sendKeys(password);
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		loginPage.clickOnloginbtn().click();
	}

	@Then("Verify user is able to successfully login")
	public void verify_user_is_able_to_successfully_login() {
		boolean result = loginPage.editAccountInformation().isDisplayed();
		Assert.assertTrue(result);
		
	}
	
	@After
	public void closeBrowser() {
		driver.quit();
	}

}

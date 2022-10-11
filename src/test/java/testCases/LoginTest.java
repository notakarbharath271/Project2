package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	public WebDriver driver;

	Logger LOGGER = LogManager.getLogger(LoginTest.class);
	LandingPage landingPage;
	LoginPage loginPage;

	@BeforeMethod
	public void openURl() {
		driver = initializeBrowser();
		LOGGER.info("Open URL");
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("url"));

	}

	@Test(enabled = true)
	public void logintest() {
		LOGGER.info("Created Landing page object.");
		landingPage = new LandingPage(driver);
		LOGGER.info("By using the landing page object reference clicks on the myAccount.");
		landingPage.getMyAccount();
		LOGGER.info("By using the landing page object reference clicks on the Login.");
		landingPage.getLogin();

		LOGGER.info("Created Login page object.");
		loginPage = new LoginPage(driver);
		LOGGER.info("By using the login page object reference enter username.");
		loginPage.enterUsername().sendKeys(properties.getProperty("username"));
		LOGGER.info("By using the login page object reference enter password.");
		loginPage.enterPassword().sendKeys(properties.getProperty("password"));
		LOGGER.info("By using the login page object reference clicks on login button.");
		loginPage.clickOnloginbtn().click();
		try {
			if (loginPage.editAccountInformation().isDisplayed()) {
				LOGGER.info("verifying the edit account information link after login sucess");
				boolean actual = loginPage.editAccountInformation().isDisplayed();

				Assert.assertFalse(actual);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	@Test(dataProvider = "logindata")
	public void login(String username, String password, String expectedstatus) {
		landingPage = new LandingPage(driver);
		landingPage.getMyAccount();
		landingPage.getLogin();
		loginPage = new LoginPage(driver);
		loginPage.enterUsername().sendKeys(username);
		loginPage.enterPassword().sendKeys(password);
		loginPage.clickOnloginbtn().click();
		
		String actualStatus = null ;
		
		try {
			if (loginPage.editAccountInformation().isDisplayed()) {
				 actualStatus ="Success";
			}
		}catch (Exception e) {
			actualStatus ="Fail";
		}
		Assert.assertEquals(actualStatus, expectedstatus);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "logindata")
	public Object[][] dataProvide() {
		Object[][] data = { { "bharathk123@gmail.com", "bharathTest", "Success" },
				{ "bharathk@gmail.com", "bharathTest", "Fail" } };
		return data;

	}

}

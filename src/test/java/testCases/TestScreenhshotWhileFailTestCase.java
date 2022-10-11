package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

@Test
public class TestScreenhshotWhileFailTestCase extends Base {
	
	public WebDriver driver;
	public void test() {
	driver = initializeBrowser();	
	
	driver.get("https://mvnrepository.com/");
	String expectedtitle="Maven Repository: Search/Browse/Explore  ";
	
	
	String actualtitle = driver.getTitle();
	
	Assert.assertEquals(actualtitle, expectedtitle);
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public Properties properties;

	
	public WebDriver initializeBrowser() {
		
		 properties = new Properties();
		String proPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		//System.out.println(proPath);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(proPath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		String browser = properties.getProperty("browserName") ;
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}else {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		return driver;
	}
	
	public String takeScreenShot(String testName,WebDriver driver) {
		
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		try {
			FileUtils.copyFile(file,new File(destinationFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationFilePath;
	}

}

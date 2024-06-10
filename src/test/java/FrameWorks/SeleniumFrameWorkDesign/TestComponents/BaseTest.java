package FrameWorks.SeleniumFrameWorkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameWorks.SeleniumFrameWorkDesign.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		//Properties Class in Java
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\FrameWorks\\SeleniumFrameWork\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); //Ternary Operator
		
		
		if (browserName.contentEquals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "H:\\\\Automation\\\\chromedriver-win64\\\\chromedriver.exe");
		driver = new ChromeDriver();
		
		}
		
		else if (browserName.contentEquals("Firefox"))
		{
			//FireFox Code
		}
		
		else if (browserName.contentEquals("Edge"))
		{
			//Edge Code
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
	
		//Read json to String
	 String jsonContent = 	FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
	 //String to HashMap Jackson Databind
	 
	 ObjectMapper mapper = new ObjectMapper();
	 List <HashMap <String,String>> data = mapper.readValue(jsonContent, new TypeReference< List <HashMap<String, String>>>(){ 
	 });
	 
	 return data;
	
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file =  new File (System.getProperty("user.dir")+ "//reports//" +testcaseName+ ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" +testcaseName+ ".png";
	}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication () throws IOException
	{
		WebDriver driver = initializeDriver();
	    landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod (alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}

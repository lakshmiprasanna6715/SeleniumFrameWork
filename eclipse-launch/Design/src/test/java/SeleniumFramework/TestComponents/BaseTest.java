package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import SeleniumFramework.Design.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver intiliazationDriver() throws IOException 
	{
		//Properties Class
		Properties prop =new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFramework\\resources\\GlobalData.properties");
		prop.load(file);
		String browserName = prop.getProperty("browser");
		System.out.println("Browser from properties file: " + browserName);
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		 driver= new ChromeDriver();
		
		
		}
		else if(browserName.equalsIgnoreCase("fireFox"))
		{
			//FIreFox
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "edge.exe");
			WebDriver driver= new EdgeDriver();
		}
		else 
		{
		    throw new RuntimeException("Unsupported browser: " + browserName);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("driver is: " + driver);
		driver.manage().window().maximize();
				return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = intiliazationDriver();

		landingPage=new LandingPage(driver);
		landingPage.goTo();
		
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}
	
	//This method only execute when the testcase is fail
	public String getScreenShot(String testCaseName,WebDriver driver ) throws IOException
	{
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") +"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") +"//reports//" + testCaseName + ".png";
	}
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"xyz6715@gmail.com" ,"Xyz@_12345","ZARA COAT 3"},{"abcrio@gmail.com","Abc@_12345","ADIDAS ORIGINAL"},};
	}
	
	@DataProvider
	public Object[][] getLoginData()
	{
		return new Object[][] {{"xyz6715@gmail.com" ,"Xyz@_12345"},{"abcrio@gmail.com","Abc@_12345"},};
	
	}
	
	
	//WebElement Secondprod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findAny().orElse(null);
			//Secondprod.findElement(By.xpath("//button[@fdprocessedid='ulmbqa']")).click();

}

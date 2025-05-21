package SeleniumFramework.Design.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import SeleniumFramework.Design.UtilityClasses.AbstractComponents;

public class orderPage extends AbstractComponents {
	
	WebDriver driver;
	
	public orderPage(WebDriver driver) {
		//Here child is LandingPage and parent is AbstractComponents so we send data here calling driver from child to parent by using super keyword
		super (driver);
		//Intialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
	//PageFactory
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	public Boolean verifyProductList(String ProductName)
		{
			Boolean match = productNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
			//Assert.assertTrue(match);
			return match;
		}
		
	
	
	
	
}

package SeleniumFramework.Design.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.Design.UtilityClasses.AbstractComponents;

public class confirmationPage extends AbstractComponents{

WebDriver driver;
	
	public confirmationPage(WebDriver driver) {
		//Here child is LandingPage and parent is AbstractComponents so we send data here calling driver from child to parent by using super keyword
		super (driver);
		//Intialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//String ConfirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
	//Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.")); 
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String verifyConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
}

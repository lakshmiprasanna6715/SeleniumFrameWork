package SeleniumFramework.Design.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFramework.Design.UtilityClasses.AbstractComponents;

public class ProductCatelogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatelogue(WebDriver driver) {
		//Here child is LandingPage and parent is AbstractComponents so we send data here calling driver from child to parent by using super keyword
		super (driver);
		//Intialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	//PageFactory
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement Spinner;
	
	By productBy = By.cssSelector(".mb-3");
	By addCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod= getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addingMulProducts(List<String> productsToAdd)
	{
		for (String productName: productsToAdd)
		{
			WebElement prod = getProductByName(productName);
	        if (prod != null) {
	            prod.findElement(addCartBy).click();
	            waitForElementToAppear(toastMessage);
	           //waitForElementToDisapper();
	        } else {
	            System.out.println("Product not found: " + productName);
	        }
		}
	}
	
	
	
	public void addToCart(String ProductName) throws InterruptedException
		{
			WebElement prod = getProductByName(ProductName);
			prod.findElement(addCartBy).click();
			waitForElementToAppear(toastMessage);
			waitForElementToDisapper();
	}

	public void waitForElementToDisapper() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
	}

	
}

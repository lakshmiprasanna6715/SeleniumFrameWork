package SeleniumFramework.Design;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.Design.pageObjects.ProductCatelogue;
import SeleniumFramework.Design.pageObjects.cartPage;
import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	
	
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		String ProductName ="ZARA COAT 3";
		landingPage.LoginApplication("xyz67195@gmail.com", "Xyz@_12345");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());

		////div[@aria-label='Incorrect email or password.'] id attribute is not available for this element
		/////.ng-tns-c4-10.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String ProductName ="ZARA COAT 3";
		ProductCatelogue Catelogue = landingPage.LoginApplication("abcrio@gmail.com", "Abc@_12345");

		List<WebElement> products = Catelogue.getProductList();
		Catelogue.addToCart(ProductName); 
		
		cartPage cartPage = Catelogue.goToCart();
		Boolean match = cartPage.verifyProductList("ZARA COAT 33");
		
		//In Pageobject/Utility classes we cannot validate anything whether it is true or false we cannot implement assert also only main class
		Assert.assertFalse(match);
	
	}

}

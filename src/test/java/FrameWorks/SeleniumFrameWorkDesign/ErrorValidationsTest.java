package FrameWorks.SeleniumFrameWorkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import FrameWorks.SeleniumFrameWorkDesign.TestComponents.BaseTest;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.CartPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.CheckoutPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.ConfirmationPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.LandingPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test (groups= {"ErrorHandling"})
	public void submitOrder() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("osama.nadeem@gmail.com", "Ilikeautomation"); // Very Tricky Video 167
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErroValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("osama.nadeem@gmail.com",
				"Ilikeautomation@#07"); // Very Tricky Step Video 163

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage(); // Very Tricky Step Video 163

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}

package FrameWorks.SeleniumFrameWorkDesign;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWorks.SeleniumFrameWorkDesign.TestComponents.BaseTest;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.CartPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.CheckoutPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.ConfirmationPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.LandingPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.OrderPage;
import FrameWorks.SeleniumFrameWorkDesign.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {



		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password")); // Very Tricky Step Video 163

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage(); // Very Tricky Step Video 163

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Pakistan");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test (dependsOnMethods = {"submitOrder"}) //dependent tests
	public void OrderHistoryTest() throws InterruptedException
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("osama.nadeem@gmail.com",
				"Ilikeautomation@#07");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "osama.nadeem@gmail.com");
//		map.put("password", "Ilikeautomation@#07");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "osama.nadeem@gmail.com");
//		map1.put("password", "Ilikeautomation@#07");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\FrameWorksSeleniumFrameWorkDesign\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)} , {data.get(1)}};   //Object is parent data type.
	}

}

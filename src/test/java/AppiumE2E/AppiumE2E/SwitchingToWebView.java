package AppiumE2E.AppiumE2E;

import org.testng.annotations.Test;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.CheckOutPage;
import pageObjects.FormPage;
import pageObjects.ProductSelectionPage;

public class SwitchingToWebView extends Base {
	
	@Test
	public static void SwitchToWebViewTest() throws InterruptedException, IOException {
		
		server = startAppiumServer();
		
		AndroidDriver<AndroidElement> driver = eCommerceCapabilities("GeneralStoreApp");
		
		FormPage formPage = new FormPage(driver);
		
		String product = "Jordan 6 Rings";
		
		formPage.getNameField().sendKeys("Nick with the big dick");
		driver.hideKeyboard(); //Hides any open keyboard
		formPage.getFemaleRadioButton().click();
		
		//Let's Shop button click
		formPage.getSubmitButton().click();
		
		//Scroll and search though list of elements for Jordan 6 Rings
		Utilities utilities = new Utilities(driver);
		
		utilities.scrollToProduct(product);
		
		//Add product to cart
		int addToCartLinks = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		//Search products visibile on page by creating a list, and then selecting the appropriate add to cart link
		for(int i=0; i<addToCartLinks; i++) {
			if (driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText().equals(product)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break; //Be sure to break if product is found
			}
		}
		
		//Click on cart
		ProductSelectionPage productSelectionPage = new ProductSelectionPage(driver);
		
		productSelectionPage.getCartButton().click();
		
		//Assert gestures
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		
		TouchAction touch = new TouchAction(driver);
		touch.tap(tapOptions().withElement(element(checkOutPage.getCheckBox()))).perform();
		checkOutPage.getProceedButton().click();
		
		//Switching Context to WebView
		Thread.sleep(8000);
		Set<String> contextHandles = driver.getContextHandles();
		for(String context:contextHandles ) {
			System.out.println(context);
		}
		Thread.sleep(15000);
		driver.context("WEBVIEW_com.androidsample.generalstore"); //Switching to WebView context here
		driver.findElement(By.name("q")).sendKeys("Hello from the other side"); //Make sure Chromedriver version matches one on emulator/device. Check Appium logs.
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//Moving back to native app (Android Only)
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		
		server.stop();
		
	}

}

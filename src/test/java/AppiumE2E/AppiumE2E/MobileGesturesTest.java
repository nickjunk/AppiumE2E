package AppiumE2E.AppiumE2E;

import org.testng.annotations.Test;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckOutPage;
import pageObjects.FormPage;
import pageObjects.ProductSelectionPage;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

public class MobileGesturesTest extends Base{

	@Test
	public static void MobileGestureTest() throws IOException, InterruptedException {
		
		server = startAppiumServer();
		
		AndroidDriver<AndroidElement> driver = eCommerceCapabilities("GeneralStoreApp");
		
		String product = "Jordan 6 Rings";
		String product2 = "Air Jordan 4 Retro";
		
		FormPage formPage = new FormPage(driver);
		
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
		
		//Scroll and search though list of elements for Air Jordan 4 Retro
		utilities.scrollToProduct(product2);
		
		//Add product to cart
		int addToCartLinks2 = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		//Search products visibile on page by creating a list, and then selecting the appropriate add to cart link
		for(int i=0; i<addToCartLinks2; i++) {
			if (driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText().equals(product2)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break; //Be sure to break if product is found
			}
		}
		
		//Click on cart
		ProductSelectionPage productSelectionPage = new ProductSelectionPage(driver);
		
		productSelectionPage.getCartButton().click();
		
		//Assert gestures
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		WebElement checkBox = driver.findElement(By.className("android.widget.CheckBox"));
		
		TouchAction touch = new TouchAction(driver);
		touch.tap(tapOptions().withElement(element(checkOutPage.getCheckBox()))).perform();
		
		touch.longPress(longPressOptions().withElement(element(checkOutPage.getTAndC())).withDuration(ofSeconds(2))).release().perform();
		
		checkOutPage.getConfirmButton().click();
		
		checkOutPage.getProceedButton().click();
		
		server.stop();
		
	}

}

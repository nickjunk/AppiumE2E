package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage {
	
	public CheckOutPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(xpath="//*[@text='Please read our terms of conditions']")
	private WebElement tAndC;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement confirmButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	public WebElement getCheckBox() {
		return checkBox;
	}
	
	public WebElement getTAndC() {
		return tAndC;
	}
	
	public WebElement getConfirmButton() {
		return confirmButton;
	}
	
	public WebElement getProceedButton() {
		return proceedButton;
	}
	
}

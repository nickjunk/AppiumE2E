package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {

	public FormPage(AndroidDriver<AndroidElement> driver) {
		//can ignore AppiumFieldDecorator if working with just Selenium, but required for Appium compatability
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//*[@text='Female']")
	private WebElement femaleRadioButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitButton;
	
	public WebElement getNameField() {
		return nameField;
	}
	
	public WebElement getFemaleRadioButton() {
		return femaleRadioButton;
	}
	
	public WebElement getSubmitButton() {
		return submitButton;
	}
}

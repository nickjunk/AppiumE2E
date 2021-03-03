package AppiumE2E.AppiumE2E;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	
	AndroidDriver<AndroidElement> driver;
	
	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/AppiumE2E/AppiumE2E/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public void scrollToProduct(String product) {
		
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".resourceId(\"com.androidsample.generalstore:id/rvProductList\"))"
				+ ".scrollIntoView(new UiSelector().textMatches(\""+product+"\").instance(0));"));
		
	}
	
}

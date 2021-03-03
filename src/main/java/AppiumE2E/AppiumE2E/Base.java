package AppiumE2E.AppiumE2E;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {
	
	public static AppiumDriverLocalService server;
	public static AndroidDriver<AndroidElement> driver;
	
	public static AppiumDriverLocalService startAppiumServer() throws InterruptedException {
		
		//Make sure to set run configurations for JAVA_HOME, ANDROID_HOME, and PATH
		
		server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
	            .usingDriverExecutable(new File("/usr/local/Cellar/node/15.7.0/bin/node"))
	            .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
	            .usingPort(4723).withIPAddress("127.0.0.1"));
	    server.start();
	    Thread.sleep(15000);
	    return server;
		
	}
	
	public static void startEmulator() throws IOException, InterruptedException {
		
		Process p = Runtime.getRuntime().exec(System.getProperty("user.dir")+"/resources/emulator.command");
		p.waitFor();
		
	}
	

	public static AndroidDriver<AndroidElement> eCommerceCapabilities(String appFile) throws IOException, InterruptedException {
		
		File appDir = new File("src");
		File app = new File(appDir, Utilities.getGlobalValue(appFile));
		
		startEmulator();
		
		DesiredCapabilities dC = new DesiredCapabilities();
		dC.setCapability(MobileCapabilityType.DEVICE_NAME, Utilities.getGlobalValue("device"));
		dC.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dC.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		dC.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		//dC.setCapability("appPackage", "com.android.contacts"); --> How to get to contacts Native app, check package name
		//dC.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
		
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dC);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Setting global implicit wait
		
		return driver;
		
	}
	
}

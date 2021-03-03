package resources;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{
	
	public void onTestFailure(ITestResult result) {
		//Screenshot
		//Print response
		//etc.
		System.out.println("You're a failure. "+result.getName()); //Can even print test method that fails
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Success!!");
	}
	
}

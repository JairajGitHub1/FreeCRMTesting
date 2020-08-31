package com.crm.qa.ExtentReportListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.crm.qa.base.ObjectsRepo;

import io.qameta.allure.Attachment;


//Note: Listeners must be called for execution to start (Preferred from TestNG.xml)
public class ExtentReportNewListenerImplementation extends ObjectsRepo implements ITestListener {

	
	//=======================================================================================
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}
	
	//=======================================================================================
	
	
	
	//Right Click (inside here) --> Source --> Override / Implements Method
	// We will implement all methods here.
	
	@Override
	public void onStart(ITestContext context) {
		
		System.out.println("Inside: Extent Report New Listener Implementation");
		System.out.println("I am in onStart method " + context.getName());
		context.setAttribute("WebDriver", ObjectsRepo.getDriver());
		
		//Call the Extent Report Setup method (This will return the object of extent report)
		extent = ExtentReportNewSetup.setupExtentReport();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("I am in onFinish method " + context.getName());
		
		//close the Extent Report 
		extent.flush();	
		
		// Added the below code
		driver.quit();
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");
		//Will execute before each test case
		ExtenttestStep = extent.createTest(result.getMethod().getMethodName());
	}

	
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("I am in onTestSuccess method " + getTestMethodName(result) + " succeed");
		// On Success this method will be called
		ExtenttestStep.log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " : Passed");
	}

	
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println("I am in onTestFailure method " + getTestMethodName(result) + " failed");
		
		Object testClass = result.getInstance();
		//WebDriver driver = ObjectsRepo.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(result));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");	
		
		
		
		
		
		// On Failure this method will be called
		ExtenttestStep.log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " : Failed");
		ExtenttestStep.log(Status.FAIL,"Click: Below to see failure details");
		ExtenttestStep.log(Status.FAIL,result.getThrowable());	
		///////////// Convert This to a Method ////////////////////////////////
		//Add Screenshot for failed Test
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//Simple date formatter
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy' 'HH-mm-ss");
		System.out.println("Date Format: "+ format);
		//SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		//////////////////////////////////////////////////////////////////////
		
		// Get Current Date
		Date date = new Date();
		String actualDate = format.format(date);
		System.out.println("ExtentReportNewListenerImplementation : Date Format: "+ actualDate);	
		//Create Screenshot Path
		String screenshotPath = System.getProperty("user.dir") + "\\Reports\\Screenshots_"+actualDate+".png";
		
		File dest = new File(screenshotPath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ExtenttestStep.addScreenCaptureFromPath(screenshotPath, "Failure: Screenshot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(result) + " skipped");
		// On Skip this method will be called
		ExtenttestStep.log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " : Skipped");
	}

	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
	
	
//
//	@Override
//	public void onTestFailedWithTimeout(ITestResult result) {
//
//	}





}

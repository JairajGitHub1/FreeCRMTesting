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
public class ExtentReportNewListenerImplementation_Backup extends ObjectsRepo implements ITestListener {
	
	
	//Right Click (inside here) --> Source --> Override / Implements Method
	// We will implement all methods here.
	
	@Override
	public void onTestStart(ITestResult result) {
		//Will execute before each test case
		ExtenttestStep = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// On Success this method will be called
		ExtenttestStep.log(Status.PASS, "Test Case: " + result.getMethod().getMethodName() + " : Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// On Failure this method will be called
		ExtenttestStep.log(Status.FAIL, "Test Case: " + result.getMethod().getMethodName() + " : Failed");
		ExtenttestStep.log(Status.FAIL,"Please click below for details: \n " + result.getThrowable());
		
		///////////// Convert This to a Method ////////////////////////////////
		//Add Screenshot for failed Test
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//Simple date formatter
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy hh-mm-ss");
		//////////////////////////////////////////////////////////////////////
		
		// Get Current Date
		Date date = new Date();
		String actualDate = format.format(date);
	
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
		// On Skip this method will be called
		ExtenttestStep.log(Status.SKIP, "Test Case: " + result.getMethod().getMethodName() + " : Skipped");
	}

//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//
//	}
//
//	@Override
//	public void onTestFailedWithTimeout(ITestResult result) {
//
//	}

	@Override
	public void onStart(ITestContext context) {
		
		System.out.println("Inside: Extent Report New Listener Implementation");
		
		//Call the Extent Report Setup method (This will return the object of extent report)
		extent = ExtentReportNewSetup.setupExtentReport();

	}

	@Override
	public void onFinish(ITestContext context) {
		//close the Extent Report 
		extent.flush();	
		
		// Added the below code
		driver.quit();
		System.out.println("<========================== Teardown Completed: Quit the driver and close all associated windows ==========================>");
		
	}
	

}

package com.crm.qa.base;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ObjectsRepo {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest ExtenttestStep;

	public String buildNo = null;
	
	public static LoginPage loginPage;
	public static HomePage homePage;
	public static ContactsPage contactsPage;
	public static TestUtil testUtil;
	public static Object getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

//	We may require initializing class objects in each Tests like: Login page / Home Page / Contacts etc. However this issue may grow if we need to access those objects in end to end. 
//	Hence, it is a good practice to initialize these objects in different class:
//		1)	 TestBase class 
//		2)	ObjectRepo class (Better Approach)
//
//		Example: (class objects initialized)
//		Under LoginPageTest:
//			// Class Object initialized
//		•	LoginPage loginPage; // since we want to use this variable across the class hence, declaring here.
//		•	HomePage homePage; // since login process once completed will return homepage class
	
//	Under HomePageTest:
//		// Class Object initialized
//	•	LoginPage loginPage; // since we want to use this variable across the class hence, declaring here.
//	•	HomePage homePage; // since login process once completed will return homepage class
//	•	TestUtil testUtil; // TestUtil class object
//	•	ContactsPage contactsPage; // ContactsPage Class Object initialized
	

//	Under ContactsPageTest: 
//		// Class Object initialized
//	•	LoginPage loginPage;
//	•	HomePage homePage;
//	•	TestUtil testUtil;
//	•	ContactsPage contactsPage;
	
	//Since Multiple inheritance is not possible hence, we will extend "ObjectsRepo" class in "TestBase Class"
	// All Test cases are extending the "TestBase Class"
	// Just import the class: import com.crm.qa.pages.HomePage, com.crm.qa.pages.LoginPage
	//We can call them in any test like:  
	//                                   loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

		

}

package com.crm.qa.ExtentReportListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.qa.base.ObjectsRepo;


public class ExtentReportNewSetup extends ObjectsRepo {
	

	//ExtentReports extent;
	
	public static ExtentReports setupExtentReport() {
		
		//Simple date formatter
		//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy' 'HH-mm-ss");
		//SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		// Get Current Date
		Date date = new Date();
		String actualDate = format.format(date);
		
		
		// Using Spark Reporter:let us create a folder called Reports where I will store all my executions and screenshots
		System.out.println("Inside: Extent Report New Setup. Java");
		
		String reportPath = System.getProperty("user.dir") + "\\Reports\\NewExtentReport_"+actualDate+".html";
		System.out.println("ExtentReportNewSetup : Report Path (Date Format): "+ actualDate);
		
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(reportPath);		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		System.out.println("sparkReporter");

		sparkReporter.config().setDocumentTitle("CWC_LLA_Automation_Report");
		sparkReporter.config().setReportName("Automation Test Execution Report");		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().enableTimeline(true);
		//sparkReporter.config().setEncoding("utf-8");
		//sparkReporter.config().setCSS("css-string");
		//sparkReporter.config().setJS("js-string");		
		sparkReporter.config().setProtocol(Protocol.HTTPS);;
		//htmlReporter.config().setCSS(".subview-left{width: 18%;}");

		extent.setSystemInfo("OS : ", System.getProperty("os.name"));
		extent.setSystemInfo("Browser : ", System.getProperty("browser.name"));
		extent.setSystemInfo("OS Architecture : ", System.getProperty("os.arch"));

		extent.setSystemInfo("User Name : ", System.getProperty("user.name"));
		extent.setSystemInfo("User Directory : ", System.getProperty("user.dir"));
		extent.setSystemInfo("Java Version : ", System.getProperty("java.version"));
		extent.setSystemInfo("Java Runtime (JVM) : ", System.getProperty("java.runtime.version"));
		extent.setSystemInfo("Java Compiler : ", System.getProperty("java.compiler"));
		extent.setSystemInfo("Java Home : ", System.getProperty("java.home"));
		
		extent.setSystemInfo("Name : ", System.getProperty("name"));		
		extent.setSystemInfo("Machine Name : ", System.getProperty("machine.name"));
		extent.setSystemInfo("IP Address : ", System.getProperty("machine.address"));
		extent.setSystemInfo("Browser Name: ", "Chrome | FF");
		
//       extent.setSystemInfo("QA Build# (Jenkins) :", buildNo);
//		 report.setSystemInfo("Environment", box);
//		 report.setSystemInfo("Test Type", testtype);
//		 report.setSystemInfo("Browser", br);

		
//		htmlReporter = new ExtentHtmlReporter("./src/eFollettReports/" + br+"_SPA_Automation_Report.html");
//		//report = new ExtentReports(Config.EXTENT_REPORT_PATH + br + "_TestResults.html");
//		 htmlReporter.loadXMLConfig(new File(Config.EXTENT_CONFIG_XML_PATH));
//		 report = new ExtentReports();
//		 report.attachReporter(htmlReporter);
//		 report.setSystemInfo("QA Build# (Jenkins) :", buildNo);
//		 report.setSystemInfo("Environment", box);
//		 report.setSystemInfo("Test Type", testtype);
//		 report.setSystemInfo("Browser", br);
//		 
//		 //htmlReporter.config().setAutoCreateRelativePathMedia(true);
//		 htmlReporter.config().setDocumentTitle("eFollett SPA Automation");
//		 htmlReporter.config().setReportName("SPA  :  Automated Test Execution Report  :~   " + testtype + "   |  "  + box +  "  |");
//		 //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//		 htmlReporter.config().setTheme(Theme.DARK);
//		//htmlReporter.config().setTheme(Theme.STANDARD);
		
//		htmlReporter.Config.CSS = "css-string";
//		htmlReporter.Config.DocumentTitle = "page title";
//		htmlReporter.Config.EnableTimeline = true;
//		htmlReporter.Config.Encoding = "utf-8";
//		htmlReporter.Config.JS = "js-string";
//		htmlReporter.Config.Protocol = Protocol.HTTPS;
//		htmlReporter.Config.ReportName = "build name";
//		htmlReporter.Config.Theme = Theme.DARK;

		//Now we should return this object so that it is available where ever we call it.
		
		return extent;
		
		
		
	}

}

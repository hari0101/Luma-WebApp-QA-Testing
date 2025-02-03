package com.webdriver.Listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.utilities.CommonUtils.LoggerManager;

public class LoggerListener implements IInvokedMethodListener, ISuiteListener {
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	private long startTime;
	private Logger logger = LoggerManager.getInstance().getLogger(this.getClass());
	
	//=========================================== METHODS ===========================================
	@Override
	public void onStart(ISuite suite) {
		startTime = System.currentTimeMillis();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
		logger.info("[Test-Suite] - Test Suite Started: {}", suite.getName());
		logger.info("[Test-Suite] - Execution Start Time: {}", now.format(format));
		
	}

	@Override
	public void onFinish(ISuite suite) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
		logger.info("[Test-Suite] - Test Suite Completed: {}" , suite.getName());
		logger.info("[Test-Suite] - Total time taken for the suite \"{}\" : {} seconds", suite.getName(), this.getTotalTime());
		logger.error("[Test-Suite] - Total Failed Tests in the suite \"{}\" : {}", suite.getName(), this.totalFailTest(suite));
		logger.info("[Test-Suite] - Execution End Time: {}" , now.format(format));
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		String classname = result.getTestClass().getName().toUpperCase();	
		String methodname = result.getMethod().getMethodName().toUpperCase();
		
		//Condition for testNG @BeforeTest
		if(method.getTestMethod().isBeforeTestConfiguration()) {
			logger.info("[BeforeTest-Method] - {} has been started executing from [Test-Class] {}", methodname, classname);
		}
		
		//Condition for testNG @AfterTest
		if(method.getTestMethod().isAfterTestConfiguration()) {
			logger.info("[AfterTest-Method] - {} has been started executing from [Test-Class] {}", methodname, classname);
		}
		
		//Condition for testNG @Test
		if(method.isTestMethod()) {
			logger.info("[Test-Method] - {} has been started executing from [Test-Class] - {}", methodname, classname );
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		int status = result.getStatus();
		String methodname = result.getMethod().getMethodName().toUpperCase();
		
		//Condition for testNG @BeforeTest
		if(method.getTestMethod().isBeforeTestConfiguration()) {
			logger.info("[BeforeTest-Method] - {}: Passed and execution has been completed!", methodname);
		}
		
		//Condition for testNG @AfterTest
		if(method.getTestMethod().isAfterTestConfiguration()) {
			logger.info("[AfterTest-Method] - {}: Passed and execution has been completed!", methodname);
		}
		
		//Condition for testNG @Test
		if(method.isTestMethod()) {
			if(ITestResult.SUCCESS == status) {
				logger.info("[Test-Method] - {}: Passed and execution has been completed!", methodname);
			}
		
			if(ITestResult.FAILURE == status) {
				logger.error("[Test-Method] - {}: Failed and execution has been incomplete!", methodname);
				logger.error("[Test-Method] - Due to: " + result.getThrowable().getMessage());
			}
		
			if(ITestResult.SKIP == status) {
				logger.warn("[Test-Method] - {}: Skipped and execution has been Ignored!", methodname);
			}
		}	
	
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		String machine = context.getCurrentXmlTest().getParameter("Machine");
		String browser =context.getCurrentXmlTest().getParameter("Browser");
		String headless =context.getCurrentXmlTest().getParameter("headless");
		String incognito =context.getCurrentXmlTest().getParameter("incognito");
		
		//Condition for testNG @BeforeTest
		if(method.getTestMethod().isBeforeTestConfiguration()) {
			
			logger.info("[Test-Parameter] - Current test execution running in the \"{}\" Machine", machine.toUpperCase());
			logger.info("[Test-Parameter] - Current test Browsertype : {}", browser);
			logger.info("[Test-Parameter] - Current test execution in Headless mode : {}", headless);
			logger.info("[Test-Parameter] - Current test execution in Incognito mode : {}", incognito);
		}
	}
	
	/** @returns total failed tests in the suite */
	private int totalFailTest(ISuite suite) {
		Map<String, ISuiteResult> maps= suite.getResults();
		int failTest = 0;
		for(ISuiteResult result : maps.values()) {
				ITestContext context = result.getTestContext();
				failTest += context.getFailedTests().size();
		}
		return failTest;
	}
	
	/** @returns total time taken for executing overall test */
	private String getTotalTime() {
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		double timeInSeconds = resultTime / 1000.00;
		return String.format("%.2f", timeInSeconds);
	}
	
	
}

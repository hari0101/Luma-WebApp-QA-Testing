package com.webdriver.Listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.utilities.CommonUtils.FailTestScreenshot;

/**
 * BaseListener.class is the composite class for handling more than two listener classes.
 * 
 * {@link LoggerListener.class - Generates common logs the testNG annotations.
 * 		  FailTestScreenshot.class - capture screenshots while test is failed.}
 * 
 */
public class BaseListener implements IInvokedMethodListener, ISuiteListener, ITestListener {
	
	//==================================== FIELDS & CONSTRUCTOR ====================================
	
	private LoggerListener logListener = new LoggerListener();
	private FailTestScreenshot failScreenshot = new FailTestScreenshot();
	
	//=========================================== METHODS ===========================================
	
	@Override
	public void onStart(ISuite suite) {
		logListener.onStart(suite);
	}

	@Override
	public void onFinish(ISuite suite) {
		logListener.onFinish(suite);
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		logListener.beforeInvocation(method, testResult);
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		logListener.afterInvocation(method, testResult);
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		logListener.beforeInvocation(method, testResult, context);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		failScreenshot.onTestFailure(result);
	}
	
	
	
}

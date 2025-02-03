package com.webdriver.Listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class ITransformerListener implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(com.webdriver.Listeners.RetryListener.class);
	}
	

	
}

	
	
	


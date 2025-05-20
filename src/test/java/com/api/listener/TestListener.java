package com.api.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TestListener implements ITestListener {

    private static final Logger logger = Logger.getLogger(TestListener.class.getName());
    public void onStart(ITestContext context)
    {
        logger.info("Test Started");
    }
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Successfull: "+result.getMethod().getMethodName());
        logger.info("Test Successfull Description: "+result.getMethod().getDescription());
        // not implemented
    }
    public void onTestFailure(ITestResult result) {
        logger.info("Test Failed: "+result.getMethod().getMethodName());
        logger.info("Test Failed: "+result.getMethod().getDescription());
        // not implemented
    }

}

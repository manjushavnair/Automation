package com.automation.ui.base.common.testnglisteners;

import com.automation.ui.base.common.core.assertion.Assertion;
import com.automation.ui.base.common.core.annotations.DontRun;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.exceptions.TestFailedException;
import com.automation.ui.base.common.logging.Log;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.lang.reflect.Method;
import java.util.List;


public class InvokeMethodAdapter implements IInvokedMethodListener {

    private static Logger logger = Logger.getLogger(InvokeMethodAdapter.class);

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod()) {
            List verificationFailures = Assertion.getVerificationFailures(result);
            if (Log.getVerificationStack().contains(false)) {
                result.setStatus(ITestResult.FAILURE);
                if (result.getThrowable() == null) {
                    result.setThrowable(new TestFailedException(null));
                }
            }
            if (verificationFailures.size() > 0) {
                result.setStatus(ITestResult.FAILURE);
                for (Object failure : verificationFailures) {
                    result.setThrowable((Throwable) failure);
                }
            }
        } else {
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.info("TEST CONFIGURATION FAILED, IS APPLICATION RUNNING, BROWSER DRIVERS CONFIGURED");
                Log.logError("TEST CONFIGURATION FAILED", result.getThrowable());
            }
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && isTestExcludedFromEnv(method.getTestMethod().getConstructorOrMethod().getMethod())) {
            throw new SkipException("Test can't be run on " + Configuration.getEnv() + " environment");
        }
    }

    /**
     * Returns true if test is excluded from running on current test environment
     */
    private boolean isTestExcludedFromEnv(Method method) {
        if (method.isAnnotationPresent(DontRun.class)) {
            String[] excludedEnvs = method.getAnnotation(DontRun.class).env();

            for (String excludedEnv : excludedEnvs) {
                if (Configuration.getEnv().contains(excludedEnv)) {
                    return true;
                }
            }
        }
        return false;
    }
}

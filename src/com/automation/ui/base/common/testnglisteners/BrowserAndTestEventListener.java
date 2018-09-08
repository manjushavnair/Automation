package com.automation.ui.base.common.testnglisteners;

import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.AlertHandler;
import com.automation.ui.base.common.core.SelectorStack;
import com.automation.ui.base.common.core.TestContext;
import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.annotations.DontRun;
import com.automation.ui.base.common.core.annotations.Execute;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.element.JavascriptActions;
import com.automation.ui.base.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.automation.ui.base.common.driverprovider.DriverProvider;
import com.automation.ui.base.common.exception.BusinessException;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.common.logging.VelocityWrapper;
import com.automation.ui.base.common.report.filehandler.CreateHTML;
import com.automation.ui.base.common.report.filehandler.FileNameConstants;
import com.automation.ui.base.common.utils.CommonUtils;
import com.automation.ui.base.common.utils.CookieUtils;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;

public class BrowserAndTestEventListener extends AbstractWebDriverEventListener
        implements ITestListener {

    private static Logger logger = Logger.getLogger(BrowserAndTestEventListener.class);
    private By lastFindBy;
    private UIWebDriver driver;

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        // driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        //for ie
        new JavascriptActions(driver).execute("window.stop");
        //for chrome
        //new JavascriptActions(driver).execute("window.stop()");

        // new JavascriptActions(driver).execute("window.setTimeout(arguments[arguments.length - 1], 5000);");

        Log.ok("NavigatePage to", VelocityWrapper.fillLink(url, url));
        Log.logJSError();
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        Method method = TestContext.getCurrentTestMethod();
        Cookie cookie = null;
        if (method != null) {
            Class<?> declaringClass = method.getDeclaringClass();
            String cookieDomain = null;
            String cookieName = null;
            //for localhost:9000 or localhost/ without domain
            if (Configuration.getEnvType().getKey().contains("test")) {
                cookieDomain = String.format("%s", Configuration.getEnvType().getSiteDomain());
                cookieName = Configuration.getEnvType().getSiteDomain();
            } else {
                cookieDomain = String.format(".%s", Configuration.getEnvType().getSiteDomain());
                cookieName = Configuration.getEnvType().getSiteDomain();
            }

            // logger.info(" cookieDomain afterNavigateTo " + cookieDomain + " Configuration.getEnvType().getSiteDomain() :" + Configuration.getEnvType().getSiteDomain()+":");

            Date cookieDate = new Date(new DateTime().plusYears(10).getMillis());

            if (!AlertHandler.isAlertPresent(driver)) {
                String command = "Url after navigation";
                if (url.equals(driver.getCurrentUrl())) {
                    Log.ok(command, VelocityWrapper.fillLink(driver.getCurrentUrl(), driver.getCurrentUrl()));
                } else {
                    // A fast lane to stop executing any test on "not a valid community" page
                    if (driver.getCurrentUrl().contains(URLsContent.NOT_A_VALID_COMMUNITY)) {
                        throw new SkipException(String.format("Wrong redirect to: %s", driver.getCurrentUrl()));
                    }
                    if (driver.getCurrentUrl().contains("data:text/html,chromewebdata ")) {
                        driver.get(url);
                        Log.warning(command, driver.getCurrentUrl());
                    } else {
                        Log.warning(command, driver.getCurrentUrl());
                    }
                }
            } else {
                Log.warning("Url after navigation", "Unable to check URL after navigation - alert present");
            }


            if (driver.getCurrentUrl().contains(Configuration.getEnvType().getSiteDomain())) {


                /**
                 * All of tests should be executed as an user who opted in (agreed) on using ads tracking.
                 * Manually user would need to click 'agree' in the tracking opt in modal.
                 */

                if (TestContext.isFirstLoad()) {
                    boolean userOptedIn = true;
                    boolean userOptedOut = false;


                    try {
                        JavascriptExecutor js = DriverProvider.getActiveDriver();
                        Object mobileSiteVersion = js.executeScript(
                                "return requirejs.entries['mobile-site/config/environment'].module.exports.default.APP.version");
                        Configuration.setTestValue("mobileSiteVersion", mobileSiteVersion.toString());
                    } catch (WebDriverException e) {
                        Configuration.setTestValue("mobileSiteVersion", null);
                    }

                    if (method.isAnnotationPresent(Execute.class) && !method.getAnnotation(Execute.class)
                            .trackingOptIn()) {
                        userOptedIn = false;
                    }

                    if (method.isAnnotationPresent(Execute.class) && method.getAnnotation(Execute.class)
                            .trackingOptOut()) {
                        userOptedOut = true;
                    }


                    if (userOptedIn) {
                        // if(!Configuration.getEnvType().getKey().contains("test"))
                        {

                            cookie = CookieUtils.addCookie("sitename", "accepted", cookieDomain, "/",
                                    cookieDate);

                            driver.manage().addCookie(cookie);
                        }
                    } else if (userOptedOut) {


                        cookie = CookieUtils.addCookie("sitename", "rejected", cookieDomain, "/",
                                cookieDate);

                        driver.manage().addCookie(cookie);
                    }
                }


            }


            if (TestContext.isFirstLoad()) {

                User user = null;
                TestContext.setFirstLoad(false);

                if (declaringClass.isAnnotationPresent(Execute.class)) {
                    user = declaringClass.getAnnotation(Execute.class).asUser();
                }


                if (method.isAnnotationPresent(Execute.class)) {
                    user = method.getAnnotation(Execute.class).asUser();
                }

                if (user != null && user != User.ANONYMOUS) {
                    // log in, make sure user is logged in and flow is on the requested url
                    //NEED TO CHECK
                    BasePageObject basePObject = new SiteBasePageObject();
                    basePObject.loginAs(user);
                }


                NetworkTrafficInterceptor
                        networkTrafficInterceptor =
                        DriverProvider.getActiveDriver().getProxy();
                if (networkTrafficInterceptor != null) {
                    networkTrafficInterceptor.startIntercepting();
                }
            }
        }
        Log.logJSError();
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
        SelectorStack.write(by);
        if (element != null) {
            SelectorStack.contextWrite();
        }
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        Log.logJSError();
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        Log.info("click", lastFindBy.toString());
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        Log.info("ChangeValueOfField", lastFindBy.toString());
    }

    @Override
    public void onTestStart(ITestResult result) {
        //  logger.info("onTestStart");


        Log.clearLogStack();
        String testName = result.getName();
        String className = result.getTestClass().getName();
        System.out.println(className + " " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // logger.info("onTestSuccess");
        try {
            Log.stop();
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.info("onTestSuccess Finally close if issue");
            DriverProvider.close();
        } finally {


        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // logger.info("onTestFailure");

        driver = DriverProvider.getActiveDriver();


        if ("true".equals(Configuration.getLogEnabled())) {
            Log.logError("Test Failed", result.getThrowable());
            Log.logJSError();
            try {
                Log.stop();
            } catch (BusinessException e) {
                e.printStackTrace();
                 logger.info("onTestFailure close if issue");
                DriverProvider.close();

            } finally {





            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //  logger.info("onTestSkipped");
        if (!Log.isTestStarted()) {
            Log.startTest(result.getMethod().getConstructorOrMethod().getMethod());
        }
        if (result.getMethod().getConstructorOrMethod().getMethod()
                .isAnnotationPresent(DontRun.class)) {
            Log.ok("Test SKIPPED", "this test is not supported in this environment");
            result.setStatus(ITestResult.SUCCESS);
            onTestSuccess(result);
        } else {
            result.setStatus(ITestResult.FAILURE);
            if (result.getThrowable() == null) {
                result.setThrowable(new SkipException("TEST SKIPPED"));
            }
            onTestFailure(result);
        }
        if (Log.isTestStarted()) {
            try {
                Log.stop();
            } catch (BusinessException e) {
                e.printStackTrace();
                //logger.info("onTestSkipped   close if issue");
                DriverProvider.close();
            } finally {


            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


        Log.ok("Percentage ", " Success");
    }

    @Override
    public void onStart(ITestContext context) {
        //NEED TO CHECK THE REPORT


        Log.startReport();
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        Log.ok("NavigatePage Back", "attempting to navigate back");
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        Log.log("NavigatePage Back", "previous page loaded", true);
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        Log.ok("NavigatePage Forward", "attempting to navigate forward");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        Log.log("NavigatePage Forward", "forward page loaded", true);
    }

    @Override
    public void onFinish(ITestContext context) {
        int suiteIndex = 1;
        CommonUtils.appendTextToFile(Log.LOG_PATH, CreateHTML.createHTML(FileNameConstants.ROOT_FOLDER + File.separator + FileNameConstants.DASHBOARD_HTML + "-"
                + suiteIndex + ".html"));
        CommonUtils.appendTextToFile(Log.LOG_PATH, "</body></html>");


    }
}

package com.automation.ui.base.common.core.element;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.CommonExpectedConditions;
import com.automation.ui.base.common.core.configuration.*;
import com.automation.ui.base.common.core.SelectorStack;
import com.automation.ui.base.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.automation.ui.base.common.logging.Log;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Wait {

    /**
     * Checks if the element is present in browser DOM
     */
    private static Logger logger = Logger
            .getLogger(Wait.class);

    private WebDriverWait wait;
    private WebDriverWait sleepingWait;
    private WebDriver driver;

    public Wait(WebDriver webDriver) {
        this.driver = webDriver;

        this.wait = new WebDriverWait(webDriver, Configuration.getDefaultTimeOut());
        this.sleepingWait = new WebDriverWait(webDriver, Configuration.getDefaultTimeOut(), BASEConstants.DEFAULT_SLEEP);
    }

    /**
     * Checks if the element is present in browser DOM
     * @return WebElement
     */
    public WebElement forElementPresent(By by) {
        return forElementPresent(by, true);
    }

    /**
     * Checks if the element is present in browser DOM
     * @return WebElement
     */
    public WebElement forElementPresent(By by, boolean failOnTimeout) {
        changeImplicitWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            if (failOnTimeout) {
                Log.log(BASEConstants.ELEMENT_PRESENT_MESSAGE,
                        String.format(BASEConstants.ELEMENT_PRESENT_ERROR_FORMAT, by.toString()), false);
            }

            throw e;
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is present in browser DOM
     * @return WebElement
     */
    public WebElement forElementPresent(By by, int timeout) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout).until(ExpectedConditions
                    .presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            Log.log(BASEConstants.ELEMENT_PRESENT_MESSAGE, e, false);
            throw e;
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is clickable in browser
     *
     * @param element The element to be checked
     *                @return WebElement
     */
    public WebElement forElementClickable(WebElement element) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            element.getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        try {
            if (SelectorStack.isContextSet()) {
                SelectorStack.contextRead();
                return wait.until(ExpectedConditions.elementToBeClickable(element));
            } else {
                return forElementClickable(SelectorStack.read());
            }
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementClickable(WebElement element, int timeout) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            element.getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        try {
            if (SelectorStack.isContextSet()) {
                SelectorStack.contextRead();
            }
            return new WebDriverWait(driver, timeout).until(ExpectedConditions
                    .elementToBeClickable(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementClickable(List<WebElement> elements, int index, int timeout) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            elements.get(index).getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        try {
            SelectorStack.contextRead();
            return new WebDriverWait(driver, timeout).until(
                    ExpectedConditions.elementToBeClickable(elements.get(index)));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is clickable on the browser
     * @return WebElement
     */
    public WebElement forElementClickable(By by) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Checks if the element is clickable on the browser
     * @return WebElement
     */
    public WebElement forElementClickable(By by, int timeout) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout).until(ExpectedConditions
                    .elementToBeClickable(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }


    /**
     * Checks if the element is visible on the browser
     *  @return WebElement
     */
    public WebElement forElementVisible(By by) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }


    /**
     * Checks if the element is visible in browser
     *
     * @param element The element to be checked
     */

    public WebElement forElementVisible(WebElement element) {

        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            //    logger.info("element.getTagName()"+element.getTagName().toString());
            element.getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        if (SelectorStack.isContextSet()) {
            SelectorStack.contextRead();
            return wait.until(ExpectedConditions.visibilityOf(element));
        } else {
            return forElementVisible(SelectorStack.read());
        }
    }

    public WebElement forElementVisible(WebElement element, int timeoutSec, int polling) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeoutSec, polling).until(ExpectedConditions
                    .visibilityOf(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementVisible(WebElement element, int timeoutSec) {
        return forElementVisible(element, timeoutSec, 500);
    }

    public WebElement forElementVisible(By selector, int timeoutSec) {
        return forElementVisible(selector, timeoutSec, 500);
    }

    public WebElement forElementVisible(By selector, Duration duration) {
        return forElementVisible(selector, (int) duration.getSeconds(), 500);
    }


    /**
     * @deprecated use method with Duration object except int
     */
    @Deprecated
    public WebElement forElementVisible(By by, int timeoutSec, int polling) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeoutSec, polling).until(
                    ExpectedConditions.visibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public WebElement forElementVisible(By by, Duration duration, int polling) {
        return forElementVisible(by, (int) duration.getSeconds(), polling);
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     */
    public boolean forElementNotVisible(By by) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, Configuration.getDefaultTimeOut()).until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     *  @return boolean
     */
    public boolean forElementNotVisible(WebElement element) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, Configuration.getDefaultTimeOut()).until(
                    CommonExpectedConditions.invisibilityOfElementLocated(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     *  @return boolean
     */
    public boolean forElementNotVisible(By by, int timeout, int polling) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout, polling).until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be either invisible or not present on the DOM.
     *  @return boolean
     */
    public boolean forElementNotVisible(By by, Duration timeout) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return new WebDriverWait(driver, timeout.getSeconds()).until(
                    ExpectedConditions.invisibilityOfElementLocated(by));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to be in viewport Either position top or left is bigger then -1
     *  @return boolean
     */
    public boolean forElementInViewPort(WebElement element) {
        changeImplicitWait(BASEConstants.WAITTIME250MILLISEC, TimeUnit.MILLISECONDS);
        try {
            return wait.until(CommonExpectedConditions.elementInViewPort(element));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forValueToBeNotPresentInElementsAttribute(
            WebElement element, String attribute, String value) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.valueToBeNotPresentInElementsAttribute(
                    element, attribute, value));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    /**
     * Wait for element to not be present in DOM
     *  @return boolean
     */
    public boolean forElementNotPresent(By selector) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.elementNotPresent(selector));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextNotInElement(WebElement element, String text) {
        try {
            element.getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            if (SelectorStack.isContextSet()) {
                SelectorStack.contextRead();
                return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(element, text));
            } else {
                return forTextNotInElement(SelectorStack.read(), text);
            }
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forValueToBePresentInElementsAttribute(
            WebElement element, String attribute, String value) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(
                    element, attribute, value));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextNotInElement(By by, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(by, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElement(By by, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBePresentInElement(by, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElement(By by, int index, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBePresentInElement(by, index, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElement(WebElement element, String text) {
        try {
            element.getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            if (SelectorStack.isContextSet()) {
                SelectorStack.contextRead();
                return wait.until(CommonExpectedConditions.textToBePresentInElement(element, text));
            } else {
                return forTextInElement(SelectorStack.read(), text);
            }
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElement(List<WebElement> elements, int index, String text) {
        try {
            elements.get(0).getTagName();
        } catch (WebDriverException e) {
            Log.info(BASEConstants.INIT_MESSAGE, BASEConstants.INIT_ERROR_MESSAGE);
        }
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            if (SelectorStack.isContextSet()) {
                SelectorStack.contextRead();
                return wait.until(CommonExpectedConditions.textToBePresentInElement(elements, index, text));
            } else {
                return forTextInElement(SelectorStack.read(), index, text);
            }
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElementAfterRefresh(WebElement element, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions.textToBePresentInElementAfterRefresh(element, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forTextInElementAfterRefresh(By by, String text) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return sleepingWait.until(CommonExpectedConditions.textToBePresentInElementAfterRefresh(by, text));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forAttributeToContain(WebElement element, String attribute, String expectedValue) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions
                    .valueToBePresentInElementsAttribute(element, attribute,
                            expectedValue));
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public boolean forAttributeToBePresent(WebElement element, String attribute) {
        changeImplicitWait(0, TimeUnit.SECONDS);
        try {
            return wait.until(CommonExpectedConditions
                    .attributeToBePresentInElement(element, attribute));
        } finally {
            restoreDeaultImplicitWait();
        }
    }


    /**
     * Wait for successful (http response code less than 400) response from specific service
     *
     * @param url Url which was used for making request
     */
    public void forSuccessfulResponse(final NetworkTrafficInterceptor networkTrafficInterceptor,
                                      final String url) {
        changeImplicitWait(0, TimeUnit.SECONDS);

        try {

            wait.until(
                    new ExpectedCondition<Boolean>() {
                        private HarEntry entry;

                        @Override
                        public Boolean apply(WebDriver webDriver) {
                            entry = networkTrafficInterceptor.getEntryByUrlPart(url);
                            return entry != null && entry.getResponse().getStatus() < 400;
                        }

                        @Override
                        public String toString() {
                            return entry == null ? String.format("sent request with url: %s", url) :
                                    String.format(
                                            "successful response (url: %s, status: %d)",
                                            url,
                                            entry.getResponse().getStatus()
                                    );
                        }
                    }
            );

        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public void forSuccessfulResponseByUrlPattern(final NetworkTrafficInterceptor trafficInterceptor,
                                                  final String pattern, int timeout) {
        changeImplicitWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        try {
            new WebDriverWait(driver, timeout).until(
                    new ExpectedCondition<Boolean>() {
                        private HarEntry entry;

                        @Override
                        public Boolean apply(WebDriver webDriver) {
                            entry = trafficInterceptor.getEntryByUrlPattern(pattern);
                            return entry != null && entry.getResponse().getStatus() < 400;
                        }

                        @Override
                        public String toString() {
                            return entry == null ? String.format("sent request matching pattern: %s", pattern) :
                                    String.format("successful response (pattern: %s)", pattern);
                        }
                    }
            );
        } finally {
            restoreDeaultImplicitWait();
        }
    }

    public void forSuccessfulResponseByUrlPattern(final NetworkTrafficInterceptor trafficInterceptor,
                                                  final String pattern) {
        forSuccessfulResponseByUrlPattern(trafficInterceptor, pattern, Configuration.getDefaultTimeOut());
    }

    public void waitForIframe(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {

        changeImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        driver.switchTo().defaultContent();
        changeImplicitWait(Configuration.getDefaultTimeOut(), TimeUnit.SECONDS);
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {

        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    public void forUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    private void restoreDeaultImplicitWait() {
        changeImplicitWait(Configuration.getDefaultTimeOut(), TimeUnit.SECONDS);
    }

    private void changeImplicitWait(int value, TimeUnit timeUnit) {

        driver.manage().timeouts().implicitlyWait(value, timeUnit == null ? TimeUnit.SECONDS : timeUnit);
        //  logger.info("wait till 2 value:"+ value + " timeUnit :"+timeUnit);
    }

    /**
     * Wait for fixed time
     *
     * @param time - in milliseconds
     */
    public void forXMilliseconds(int time) {
        forX(Duration.ofMillis(time));
    }

    public void forX(Duration duration) {
        Log.info("Wait for " + duration.toMillis() + " ms");
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Log.log("Wait.forXMilliseconds", e, false);
        }
    }

}


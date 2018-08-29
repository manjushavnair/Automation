package com.automation.ui.base.common.core.element;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.contentpatterns.XSSContent;
import com.automation.ui.base.common.driverprovider.DriverProvider;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Set of commonly used actions invoked by executing JavaScript on a web page
 */
public class JavascriptActions {


    private static Logger logger = Logger
            .getLogger(JavascriptActions.class);
    private final JavascriptExecutor js;
    private final WebDriver driver;

    public JavascriptActions(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
        this.driver = driver;
    }


    public JavascriptActions() {
        this.driver = DriverProvider.getActiveDriver();
        this.js = (JavascriptExecutor) driver;
    }

    public void click(String cssSelector) {

        js.executeScript("$('" + cssSelector + "').click()");
    }

    public void click(WebElement element) {
        js.executeScript("$(arguments[0])[0].click()", element);
    }

    public void focusCss(String cssSelector) {

        js.executeScript("$('" + cssSelector + "').focus()");
    }

    public void focus(WebElement element) {

        js.executeScript("$(arguments[0]).focus()", element);
    }

    public Object execute(String script, WebElement element) {

        return js.executeScript(script, element);
    }

    public Object executeScript(String script, Object... args) {

        return js.executeScript(script, args);
    }


    public Object execute(String script) {
        // TODO: Get rid of this wait

        try {

            //Object value1 = js.executeAsyncScript("return " + script);
            Object value = js.executeScript("return " + script);

            Thread.sleep(BASEConstants.WAITTIME1000MILLISEC);
            return value;
        } catch (InterruptedException e) {
            Log.log("execute", e, false);
            return null;
        } catch (UnsupportedOperationException e) {
            Log.log("execute", e, true);
            return null;
        }
    }

    public void mouseOver(WebElement element) {

        js.executeScript("$(arguments[0]).mouseenter()", element);
    }

    /**
     * Gets the distance from top to the bottom of the navigation bar, no matter if it's mobile or desktop.
     *
     * @return offset
     */
    private int getOffset() {
        SiteBasePageObject sitePage = new SiteBasePageObject();
        int offset = sitePage.getNavigationBarOffsetFromTop();


        return offset;
    }


    public boolean isElementInViewPort(WebElement element) {

        int offset = getOffset();

        try {
            return (Boolean) js.executeScript(
                    "return ($(window).scrollTop() + " + offset + " < $(arguments[0]).offset().top) && ($(window).scrollTop() "
                            + "+ $(window).height() > $(arguments[0]).offset().top + $(arguments[0]).height() + " + offset + ")",
                    element);
        } catch (WebDriverException e) {
            String windowScrollTop = "((window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement " +
                    "|| document.body.parentNode || document.body).scrollTop)";
            String elementOffsetTop = "(arguments[0]).offsetTop";
            String windowHeight = "(window.innerHeight)";
            String elementOuterHeight = "(arguments[0]).clientHeight";

            return (boolean) js.executeScript("return (" + windowScrollTop + " + " + offset + " < " + elementOffsetTop +
                    " && " + windowScrollTop + " + " + windowHeight + " > " + elementOffsetTop + " + " + elementOuterHeight +
                    " + " + offset + ")", element);
        }
    }


    public void scrollElementIntoViewPort(WebElement element) {
        try {
            if (!isElementInViewPort(element)) {
                scrollToElement(element);
            }
        } catch (WebDriverException e) {
            Log.info("There might be a problem with scrolling to element", e);
        }
    }


    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollDown(int pixels) {

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollIntoViewE(By elementBy) {
        scrollIntoView(driver.findElement(elementBy));
    }

    //NEEDTOCHECK
    public void scrollIntoView(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementE(By elementBy) {
        //NEEDTOCHECK
        scrollToElement(driver.findElement(elementBy));
    }


    public void scrollToElemet(WebElement element) {
        js.executeScript("window.scrollTo(arguments[0],arguments[1])",
                element.getLocation().x, element.getLocation().y);
        logger.info(element);
    }

    public void scrollToElement(WebElement element) {
        try {
            js.executeScript(
                    "window.scroll(0,parseInt($(arguments[0]).offset().top - " + getOffset() + "));", element);
        } catch (WebDriverException e) {
            if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
                Log.log("JSError", "JQuery is not defined", false);
            }
            js.executeScript(
                    "window.scroll(0,parseInt(arguments[0].getBoundingClientRect().top + window.pageYOffset - " +
                            "arguments[0].clientTop - " + getOffset() + "));", element);
        }
    }


    public void scrollToSpecificElement(WebElement element) {
        try {
            js.executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (WebDriverException e) {
            if (e.getMessage().contains(XSSContent.NO_JQUERY_ERROR)) {
                Log.log("JSError", "JQuery is not defined", false);
            }
        }
    }

    public void scrollToElement(WebElement element, int offset) {
        int elementPosition = element.getLocation().getY() - offset;
        js.executeScript(
                "window.scroll(0,arguments[0])", elementPosition
        );
    }

    public void scrollToElementInModal(WebElement element, WebElement modal) {
        int elementOffsetTop = Integer.parseInt(
                js.executeScript("return Math.round($(arguments[0]).offset().top)", element).toString());
        int modalOffsetTop = Integer.parseInt(
                js.executeScript("return Math.round($(arguments[0]).offset().top)", modal).toString());
        int scrollTop = elementOffsetTop - modalOffsetTop;

        js.executeScript("$(arguments[0]).scrollTop(arguments[1])", modal, scrollTop);
    }


    public void scrollBy(int x, int y) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
    }


    public void waitForJavaScriptTruthy(final String script) {
        driver.manage().timeouts().implicitlyWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);
        try {
            new WebDriverWait(driver, BASEConstants.WAITTIME15SEC).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    try {
                        return (boolean) js.executeScript("return !!(" + script + ");");
                    } catch (WebDriverException e) {
                        Log.logError("waitForJavaScriptTruthy", e);
                        return false;
                    }
                }
            });
        } finally {
            driver.manage().timeouts().implicitlyWait(BASEConstants.WAITTIME15SEC, TimeUnit.MILLISECONDS);
        }
    }

    public void changeElementOpacity(String selector, int value) {
        js.executeScript(
                "document.querySelector(arguments[0]).style.opacity = arguments[1];",
                selector, value);
    }

    public String getWindowErrors() {
        return js.executeScript("return window.errors || ''").toString();
    }

    public void addErrorListenerScript() {
        js.executeScript(
                "var script = document.createElement('script'); " + "script.innerHTML = 'window.onerror = "
                        + "function (e, u, l, c, errorObj) { window.errors = errorObj.stack }';"
                        + "document.querySelector('body').appendChild(script);");
    }


    public Long getCurrentPosition() {
        return (Long) js.executeScript("return window.pageYOffset;");
    }
}

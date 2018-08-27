package com.automation.ui.base.common.core.element;

import com.automation.ui.base.common.constants.BASEConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;


public class ElementStateHelper {


    private static Logger logger = Logger.getLogger(ElementStateHelper.class);

    private ElementStateHelper() {

    }


    public static boolean isElementVisible(WebElement element) {
        try {

            return element.isDisplayed();
        } catch (TimeoutException e) {
            logger.info(e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    public static boolean isElementVisible(WebElement element, WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(BASEConstants.WAITTIME500MILLISEC, TimeUnit.MILLISECONDS);

        try {

            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        } finally {
            webDriver.manage().timeouts().implicitlyWait(BASEConstants.WAITTIME5SEC, TimeUnit.SECONDS);
        }
    }
}

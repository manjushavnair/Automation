package com.automation.ui.base.common.core;

import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.ui.base.common.constants.*;

import java.util.concurrent.TimeUnit;

public class ElementStateHelper {

    public static final int TIMEOUT = 30;

    private static Logger logger = Logger.getLogger(ElementStateHelper.class);

    private ElementStateHelper() {

    }


    public static boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
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
            webDriver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        }
    }
}

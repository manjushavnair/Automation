package com.automation.ui.connected.pageobjectsfactory.componentobject.ad;

import com.automation.ui.base.common.core.UIWebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class ElementColor {
    private UIWebDriver driver;

    public ElementColor(UIWebDriver driver) {
        this.driver = driver;
    }

    public void verifyMostFrequentColor(WebElement element, Color color) {
        verifyMostFrequentColor(element, color, 30);
    }

   public void verifyMostFrequentColor(WebElement element, Color color, int timeOutInSeconds) {
      /*  AdsComparison adsComparison = new AdsComparison();
        WebDriverWait waitFor = new WebDriverWait(driver, timeOutInSeconds);

        waitFor.until(new ExpectedCondition<Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                return adsComparison.isMostFrequentColorValid(driver, element, color);
            }

            @Override
            public String toString() {
                return color + " is not most frequent on image";
            }
        });
        */
    }
}

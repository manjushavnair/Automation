package com.automation.ui.connected.pageobjectsfactory.pageobject.special;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;

public class SpecialPageObject extends SiteBasePageObject {

    private static final String HEADER_TEXT_SELECTOR = "//h1[contains(text(), '%s')]";

    public void verifyPageHeader(String expectedHeader) {
        wait.forElementVisible(By.xpath(String.format(HEADER_TEXT_SELECTOR, expectedHeader)));
        Log.log(
                "SpecialPageHeader",
                "Special Page Header is the same as expected",
                true,
                driver
        );
    }
}

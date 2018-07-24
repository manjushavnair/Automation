package com.automation.ui.connected.elements.fandom.components;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FanFeed extends BasePageObject {

    @FindBy(css = "section.fan-feed")
    private WebElement fanFeed;

    public boolean isDisplayed() {
        try {
            return fanFeed.isDisplayed();
        } catch (ElementNotFoundException e) {
            return false;
        }
    }
}

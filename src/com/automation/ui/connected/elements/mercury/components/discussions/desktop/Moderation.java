package com.automation.ui.connected.elements.mercury.components.discussions.desktop;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Moderation extends BasePageObject {

    @FindBy(className = "moderation-checkbox-label")
    private WebElement showOnlyReportedContentCheckbox;

    public Moderation checkShowOnlyReportedContentCheckbox() {
        showOnlyReportedContentCheckbox.click();
        return this;
    }
}

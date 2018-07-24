package com.automation.ui.connected.elements.mercury.components;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Head extends SiteBasePageObject {

    @FindBy(css = "head title")
    private WebElement documentTitle;

    public String getDocumentTitle() {
        return (String) jsActions.execute("return arguments[0].innerText", documentTitle);
    }
}

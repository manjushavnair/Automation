package com.automation.ui.connected.pageobjectsfactory.pageobject.actions;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePageObject extends SiteBasePageObject {

    @FindBy(css = ".mw-submit input")
    private WebElement submitButton;

    public DeletePageObject(WebDriver driver) {
        super();
    }

    public SiteBasePageObject submitDeletion() {
        wait.forElementClickable(submitButton);
        scrollAndClick(submitButton);
        Log.log("submitDeletion", "page deleted", true);
        return new SiteBasePageObject();
    }
}

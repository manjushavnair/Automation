package com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CuratedContentToolModal extends SiteBasePageObject {

    @FindBy(css = "#CuratedContentToolModal")
    protected WebElement curatedContentToolModal;

    public CuratedContentToolModal(WebDriver driver) {
        super();
    }

    public boolean isModalVisible() {
        try {
            //NEEDTOCHECK
            WebElement modal = wait.forElementVisibleW(curatedContentToolModal);
            return modal.isDisplayed();
        } catch (TimeoutException e) {
            Log.log("isModalVisible", e, false);
            return false;
        }
    }
}

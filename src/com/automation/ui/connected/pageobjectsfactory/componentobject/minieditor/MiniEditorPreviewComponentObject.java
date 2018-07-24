package com.automation.ui.connected.pageobjectsfactory.componentobject.minieditor;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MiniEditorPreviewComponentObject extends SiteBasePageObject {

    By publishButton = By.cssSelector(".buttons .primary");
    By contentWrapper = By.cssSelector("#mw-content-text");

    @FindBy(css = "#WallPreviewModal")
    private WebElement previewModal;

    public MiniEditorPreviewComponentObject(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public void verifyTextContent(String desiredText) {
        Assertion.assertEquals(previewModal.findElement(contentWrapper).getText(), desiredText);
    }

    public void publish() {
        previewModal.findElement(publishButton).click();
        Log.log("publish", "publish button clicked", true);
    }

}

package com.automation.ui.connected.pageobjectsfactory.componentobject.visualeditordialogs;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VisualEditorDialog extends SiteBasePageObject {

    @FindBy(css = ".oo-ui-window-ready")
    protected WebElement dialog;
    @FindBy(css = ".oo-ui-window-ready .oo-ui-window-frame")
    private WebElement frame;
    @FindBy(css = ".oo-ui-window-ready .oo-ui-icon-close")
    private WebElement closeButton;

    public VisualEditorDialog(WebDriver driver) {
        super();
        waitForDialogVisible();
    }

    @Deprecated
    public void switchToIFrame() {
        //NEEDTOCHECK
        wait.forElementVisibleW(dialog);
        wait.forElementVisibleW(dialog);
        driver.switchTo().frame(frame);
    }

    public void waitForDialogVisible() {
        //NEEDTOCHECK
        wait.forElementVisibleW(dialog);
    }

    @Deprecated
    public void switchOutOfIFrame() {
        waitForElementNotVisibleByElement(dialog);
        driver.switchTo().defaultContent();
    }

    public void waitForDialogNotVisible() {
        waitForElementNotVisibleByElement(dialog);
    }

    @Deprecated
    public void switchOutOfAllIFrame() {
        driver.switchTo().defaultContent();
        waitForElementNotVisibleByElement(dialog);
    }

    public VisualEditorPageObject closeDialog() {
        waitForDialogVisible();
        wait.forElementClickable(closeButton);
        closeButton.click();
        waitForDialogNotVisible();
        Log.log("closeDialog", "Dialog is closed", true);
        return new VisualEditorPageObject();
    }
}

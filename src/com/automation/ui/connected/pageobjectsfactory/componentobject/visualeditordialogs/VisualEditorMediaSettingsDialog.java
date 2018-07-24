package com.automation.ui.connected.pageobjectsfactory.componentobject.visualeditordialogs;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.common.dataprovider.VisualEditorDataProvider;
import com.automation.ui.connected.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorMediaSettingsDialog extends VisualEditorDialog {

    @FindBy(css = ".oo-ui-outlineOptionWidget")
    private List<WebElement> outlineMenuItems;
    @FindBy(css = ".oo-ui-window-ready .ve-ce-documentNode")
    private WebElement captionEditArea;
    @FindBy(css = ".oo-ui-window-foot .oo-ui-labelElement-label")
    private WebElement applyChangesButton;
    @FindBy(css = ".ve-ui-dimensionsWidget input")
    private List<WebElement> customSizeInputs;
    @FindBy(css = ".oo-ui-buttonSelectWidget a")
    private List<WebElement> positionButtons;

    private By labelElementBy = By.cssSelector(".oo-ui-labelElement-label");

    public VisualEditorMediaSettingsDialog(WebDriver driver) {
        super(driver);
    }

    public void selectSettings(VisualEditorDataProvider.Setting setting) {
        waitForDialogVisible();
        WebElement
                generalSetting =
                outlineMenuItems.get(setting.ordinal()).findElement(labelElementBy);
        wait.forElementClickable(generalSetting);
        generalSetting.click();
        Log.log("selectSettings", setting.toString() + " setting is selected", true);
        driver.switchTo().defaultContent();
    }

    public void typeCaption(String text) {
        waitForDialogVisible();
        //NEEDTOCHECK
        wait.forElementVisibleW(captionEditArea);
        captionEditArea.sendKeys(text);
        Log.log("typeCaption", "Typed " + text + " in caption area", true);
        driver.switchTo().defaultContent();
    }

    public VisualEditorPageObject clickApplyChangesButton() {
        waitForDialogVisible();
        //NEEDTOCHECK
        wait.forElementVisibleW(applyChangesButton);
        wait.forElementClickable(applyChangesButton);
        applyChangesButton.click();
        waitForDialogNotVisible();
        return new VisualEditorPageObject();
    }

    private void typeCustomSize(int size, VisualEditorDataProvider.ImageSize side) {
        WebElement customSizeInput = customSizeInput = customSizeInputs.get(side.ordinal());
        customSizeInput.clear();
        customSizeInput.sendKeys(Integer.toString(size));
        Log.log("typeCustomSize", "Typed " + size + " in the field", true, driver);
        driver.switchTo().defaultContent();
    }

    public void setCustomSize(int size, VisualEditorDataProvider.ImageSize side) {
        waitForDialogVisible();
        typeCustomSize(size, side);
        driver.switchTo().defaultContent();
    }

    public void clickAlignment(VisualEditorDataProvider.Alignment align) {
        waitForDialogVisible();
        WebElement button = positionButtons.get(align.ordinal()).findElement(labelElementBy);
        wait.forElementClickable(button);
        button.click();
        Log.log("clickAlignment", align.toString() + " align is selected", true);
        driver.switchTo().defaultContent();
    }
}

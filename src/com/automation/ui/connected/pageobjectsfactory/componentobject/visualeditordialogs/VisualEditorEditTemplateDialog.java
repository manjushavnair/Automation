package com.automation.ui.connected.pageobjectsfactory.componentobject.visualeditordialogs;

import com.automation.ui.base.common.core.interactions.Elements;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorEditTemplateDialog extends VisualEditorDialog {

    private static final By PARAM_LABEL_BY = By.cssSelector(".ve-ui-mwParameterPage-label");
    private static final By PARAM_INPUT_BY = By.cssSelector(".ve-ui-mwParameterPage-field textarea");
    private static final By TEMPLATE_PARAMS_BY = By.cssSelector(".ve-ui-mwParameterPage");
    // outside of iframe
    @FindBy(css = ".ve-ui-wikiaTemplateGetInfoWidget-templateInfoButton a")
    private WebElement getInfoLink;
    @FindBy(css = ".ve-ui-mwParameterPage")
    private List<WebElement> templateParams;
    @FindBy(css = ".oo-ui-processDialog-actions-primary a")
    private WebElement doneButton;
    @FindBy(css = ".ve-ui-wikiaTransclusionDialog-cancelButton a")
    private WebElement cancelButton;
    @FindBy(css = ".ve-ui-wikiaFocusWidget-node")
    private WebElement templateFocusedMode;

    public VisualEditorEditTemplateDialog(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForDialogVisible() {
        super.waitForDialogVisible();
        //NEEDTOCHECK
        wait.forElementVisibleW(templateFocusedMode);
    }

    @Override
    public void waitForDialogNotVisible() {
        super.waitForDialogNotVisible();
        waitForElementNotVisibleByElement(templateFocusedMode);
    }


    public void typeInParam(String paramName, String text) {
        waitForDialogVisible();
        if (isElementOnPage(TEMPLATE_PARAMS_BY)) {
            WebElement targetParam = Elements.getElementByChildText(templateParams, PARAM_LABEL_BY, paramName);
            WebElement targetParamInput = targetParam.findElement(PARAM_INPUT_BY);
            targetParamInput.sendKeys(text);
            waitForValueToBePresentInElementsAttributeByElement(targetParamInput, "value", text);
        } else {
            throw new NoSuchElementException("This template has no param.");
        }
        Log.log("typeInParam", "Type " + text + " in the " + paramName + " field.", true,
                driver);
    }

    public VisualEditorPageObject clickDone() {
        waitForDialogVisible();
        try {
            if (isElementOnPage(TEMPLATE_PARAMS_BY)) {
                wait.forElementClickable(doneButton);
                doneButton.click();
            } else {
                throw new NoSuchElementException("This template has no param.");
            }
            return new VisualEditorPageObject();
        } finally {
            waitForDialogNotVisible();
        }
    }

    public VisualEditorPageObject clickCancel() {
        waitForDialogVisible();
        try {
            if (isElementOnPage(TEMPLATE_PARAMS_BY)) {
                wait.forElementClickable(cancelButton);
                cancelButton.click();
            } else {
                throw new NoSuchElementException("This template has no param.");
            }
            return new VisualEditorPageObject();
        } finally {
            waitForDialogNotVisible();
        }
    }
}

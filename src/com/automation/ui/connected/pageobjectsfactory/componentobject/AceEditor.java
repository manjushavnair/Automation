package com.automation.ui.connected.pageobjectsfactory.componentobject;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AceEditor extends SiteBasePageObject {

    @FindBy(css = ".ace_text-layer .ace_line")
    private WebElement aceLayerTextArea;

    @FindBy(css = "textarea.ace_text-input")
    private WebElement aceInputTextArea;

    public AceEditor() {
        super();
    }

    public AceEditor clearContent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(aceLayerTextArea);
        jsActions.execute("ace.edit('editarea').setValue('');");
        Log.log("clearCssText", "ace editor was cleared", true, driver);

        return this;
    }

    public AceEditor insertContent(String cssText) {
        //NEEDTOCHECK
        wait.forElementVisibleW(aceLayerTextArea);
        jsActions.execute("ace.edit('editarea').navigateFileEnd();");
        sendContent(cssText);
        Log.log("sendAceCssText",
                "the following text was send to ace editor: " + cssText, true);

        return this;
    }

    private AceEditor sendContent(String cssText) {
        //NEEDTOCHECK
        wait.forElementVisibleW(aceLayerTextArea);
        aceInputTextArea.sendKeys(cssText);
        Log.log("sendCssText", "the following text was send to ace editor: " + cssText,
                true);

        return this;
    }

    public String getContent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(aceLayerTextArea);
        return (String) jsActions.execute("ace.edit('editarea').getSession().getValue();");
    }
}

package com.automation.ui.connected.pageobjectsfactory.pageobject.special;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialRestorePageObject extends SiteBasePageObject {

    @FindBy(css = ".mw-undelete-pagetitle")
    private WebElement articleToRestore;
    @FindBy(css = "#wpComment")
    private WebElement reasonInput;
    @FindBy(css = "#mw-undelete-submit")
    private WebElement submitRestore;

    public SpecialRestorePageObject(WebDriver driver) {
        super();
    }

    public void verifyRestoredArticleName(String articleName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(articleToRestore);
        Assertion.assertStringContains(articleToRestore.getText(), articleName);
    }

    public void giveReason(String reason) {
        wait.forElementClickable(reasonInput);
        reasonInput.sendKeys(reason);
    }

    public void restorePage() {
        wait.forElementClickable(submitRestore);
        scrollAndClick(submitRestore);
        Log.log("ArticleRestored", "Article restored", true);
    }
}

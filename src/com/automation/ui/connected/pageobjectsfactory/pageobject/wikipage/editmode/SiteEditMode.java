package com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage.editmode;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SiteEditMode extends SiteBasePageObject {

    @FindBy(css = "#wpSave")
    private WebElement publishButtonGeneral;

    /**
     * Click on Publish button
     */
    public SiteArticleEditMode clickOnPublishButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(publishButtonGeneral);
        wait.forElementClickable(publishButtonGeneral);
        publishButtonGeneral.click();
        wait.forElementPresent(editButtonBy);

        Log.log("ClickOnPublishButton", "Click on 'Publish' button", true);
        return new SiteArticleEditMode();
    }
}

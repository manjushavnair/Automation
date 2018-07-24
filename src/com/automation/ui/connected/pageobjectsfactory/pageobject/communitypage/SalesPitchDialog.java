package com.automation.ui.connected.pageobjectsfactory.pageobject.communitypage;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPitchDialog extends SiteBasePageObject {

    @FindBy(css = "#CommunityPageBenefitsModal")
    private WebElement salesPitchDialog;

    @FindBy(css = "#CommunityPageBenefitsModal .community-page-entry-point-button")
    private WebElement helpOutButton;

    @FindBy(css = ".community-page-benefits-image")
    private WebElement image;

    @FindBy(css = ".community-page-benefits-content")
    private WebElement dialogContent;

    public boolean isDialogVisible() {
        try {
            wait.forElementVisible(salesPitchDialog, 5);
            return true;
        } catch (TimeoutException e) {
            Log.log(e.getMessage(), e, true);
            return false;
        }
    }

    public SpecialCommunity clickHelpOutButton() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(helpOutButton);
            helpOutButton.click();
        } catch (TimeoutException e) {
            Log.log("Button is not visible", e, true);
        }

        return new SpecialCommunity();
    }

    public SpecialCommunity clickDialogImage() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(image);
            image.click();
        } catch (TimeoutException e) {
            Log.log("Dialog image is not visible", e, true);
        }

        return new SpecialCommunity();
    }

    public SpecialCommunity clickDialogContent() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(dialogContent);
            dialogContent.click();
        } catch (TimeoutException e) {
            Log.log("Dialog content is not visible", e, true);
        }

        return new SpecialCommunity();
    }

}

package com.automation.ui.connected.elements.oasis.pages;

import com.automation.ui.base.pageobjectsfactory.pageobject.Navigate;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPage extends SiteBasePageObject {

    private static final String PRIVACY_POLICY_PAGE = "http://www.wikia.com/Privacy_Policy";
    @FindBy(css = "#privacy-settings-button")
    private WebElement resetTrackingButton;

    public void navigateToPrivacyPolicyPage() {
        new Navigate().toUrl(PRIVACY_POLICY_PAGE);
    }

    public void clickResetTrackingButton() {
        scrollTo(resetTrackingButton);
        wait.forElementClickable(resetTrackingButton);
        resetTrackingButton.click();

    }

}

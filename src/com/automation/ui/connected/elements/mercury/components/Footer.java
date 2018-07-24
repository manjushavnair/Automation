package com.automation.ui.connected.elements.mercury.components;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Footer extends SiteBasePageObject {

    @FindBy(css = ".wds-global-footer")
    private WebElement footer;

    @FindBy(css = ".wds-global-footer__button-link")
    private WebElement viewFullSiteLink;

    @FindBy(css = ".mobile-site-link")
    private WebElement viewMobileSiteLink;

    public void clickViewFullSiteLink() {
        //NEEDTOCHECK
        wait.forElementVisibleW(footer);
        wait.forElementClickable(viewFullSiteLink);
        viewFullSiteLink.click();
    }

    public void clickViewMobileSite() {
        //NEEDTOCHECK
        wait.forElementVisibleW(footer);
        wait.forElementClickable(viewMobileSiteLink);
        scrollAndClick(viewMobileSiteLink);
    }
}

package com.automation.ui.connected.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FandomPageObject extends SiteBasePageObject {

    @FindBy(css = ".article-header__video")
    private WebElement featuredVideo;

    public boolean isFeaturedVideo() {
        //NEEDTOCHECK
        wait.forElementVisibleW(featuredVideo);
        return featuredVideo.isDisplayed();
    }

}

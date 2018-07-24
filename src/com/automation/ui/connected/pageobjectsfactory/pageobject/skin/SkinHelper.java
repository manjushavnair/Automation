package com.automation.ui.connected.pageobjectsfactory.pageobject.skin;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SkinHelper extends SiteBasePageObject {

    @FindBy(css = "body.skin-oasis")
    private WebElement oasisSkin;

    @FindBy(css = "body.discussions")
    private WebElement discussionsSkin;

    @FindBy(css = "body.mobile-wiki")
    private WebElement mobileWikiSkin;

    public SkinHelper(WebDriver driver) {
        super();
    }

    public boolean isSkin(Skin skin) {
        boolean isExpectedSkin;

        switch (skin) {
            case OASIS:
                isExpectedSkin = wait.forElementInViewPort(oasisSkin);
                break;
            case DISCUSSIONS:
                isExpectedSkin = wait.forElementInViewPort(discussionsSkin);
                break;
            case MOBILE_WIKI:
                isExpectedSkin = wait.forElementInViewPort(mobileWikiSkin);
                break;
            default:
                isExpectedSkin = false;
        }

        if (isExpectedSkin) {
            Log.info("Expected skin is loaded: " + skin.toString());
        } else {
            Log.warning("isSkin", "Expected skin is not loaded: " + skin.toString());
        }

        return isExpectedSkin;
    }
}


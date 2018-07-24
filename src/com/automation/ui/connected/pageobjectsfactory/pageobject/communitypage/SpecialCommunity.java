package com.automation.ui.connected.pageobjectsfactory.pageobject.communitypage;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpecialCommunity extends SiteBasePageObject {
    public static final String COMMUNITY_PAGE_URL = "Special:Community";
    private boolean isOpen = false;
    @FindBy(css = ".community-page-card-module .community-page-card-module-list a")
    private List<WebElement> cardsLinks;

    public boolean isCommunityPageOpen() {
        return isStringInURL(COMMUNITY_PAGE_URL);
    }

    public SpecialCommunity open() {
        getUrl(urlBuilder.getUrlForPage(COMMUNITY_PAGE_URL));
        this.isOpen = isCommunityPageOpen();

        return this;
    }

    public WebElement getLinkFromCards() {
        this.lazyOpen();

        return cardsLinks.stream().findAny().get();
    }

    private void lazyOpen() {
        if (!this.isOpen) {
            open();
        }
    }
}

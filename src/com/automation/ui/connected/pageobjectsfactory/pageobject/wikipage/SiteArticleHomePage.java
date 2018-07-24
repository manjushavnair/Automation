package com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;

public class SiteArticleHomePage extends SiteBasePageObject {

    private By wikiHomePageSpecificElement = By.className("mainpage");

    /**
     * Check if current page is in fact home page of wiki
     */
    public void verifyThisIsWikiHomePage() {
        wait.forElementPresent(wikiHomePageSpecificElement);
    }
}

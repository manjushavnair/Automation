package com.automation.ui.connected.elements.oasis.pages;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.Navigate;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends SiteBasePageObject {

    private static final String SPECIAL_SEARCH_PAGE = "/wiki/Special:Search";
    private static final String EXISTING_COMMUNITY_NAME = "pokemon";
    @FindBy(css = "#search-v2-input")
    private WebElement searchInput;
    @FindBy(css = "#search-v2-button")
    private WebElement searchButton;
    @FindBy(css = ".exact-wiki-match__result")
    private WebElement relatedCommunityModule;

    public SearchPage() {
        super();
    }

    public SearchPage navigateToSearchPage() {
        new Navigate().toPageByPath(SPECIAL_SEARCH_PAGE);

        return this;
    }

    public SearchPage typeCommunityNameInSearchInput() {
        wait.forElementClickable(searchInput);
        searchInput.sendKeys(EXISTING_COMMUNITY_NAME);

        return this;
    }

    public SearchPage clickSearchButton() {
        wait.forElementClickable(searchButton);
        searchButton.click();

        return this;
    }

    public void relatedCommunityModuleIsVisible() {
        Log.info("Is related community module visible?");
        //NEEDTOCHECK
        wait.forElementVisibleW(relatedCommunityModule);
        Log.info("Related community module is visible");
    }
}

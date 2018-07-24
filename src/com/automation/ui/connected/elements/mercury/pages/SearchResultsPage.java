package com.automation.ui.connected.elements.mercury.pages;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.elements.mercury.components.Search;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends SiteBasePageObject {
    private static final String SEARCH_RESULT_SELECTOR = ".search-results__list .wikia-card > a";
    private static final String SPINNER_SELECTOR = ".spinner";
    @Getter
    private final Search search = new Search();
    @FindBy(css = ".search-results")
    private WebElement searchResultsContainer;
    @FindBy(css = ".search-error-not-found")
    private WebElement noResultsContainer;
    @FindBy(css = ".search-error-not-found__action")
    private WebElement tryAnotherSearchLink;
    @FindBy(css = ".search-results__load-more-wrapper .wds-button")
    private WebElement loadMoreButton;
    @FindBy(css = ".search-results__list .wikia-card")
    private List<WebElement> resultCards;

    public SearchResultsPage openForQuery(String query) {
        getUrl(
                String.format("%s%s", urlBuilder.getUrl(),
                        URLsContent.MOBILE_SEARCH_RESULTS_PAGE.replace("%query%", query))
        );

        return this;
    }

    public String clickSearchResult(int index) {
        String clickedLink;

        Log.info("Select search result no.: " + index);
        WebElement searchResult = driver.findElements(By.cssSelector(SEARCH_RESULT_SELECTOR)).get(index);
        wait.forElementClickable(searchResult);

        clickedLink = searchResult.getAttribute("href");

        searchResult.click();
        waitForPageReload();

        return clickedLink;
    }

    public SearchResultsPage clickTryAnotherSearch() {
        wait.forElementClickable(this.tryAnotherSearchLink).click();
        return this;
    }

    public boolean isSearchResultsPageOpen() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(searchResultsContainer);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isNoResultsPagePresent() {
        try {
            wait.forElementVisible(noResultsContainer, 0);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLoadMoreButtonVisible() {
        return isElementVisible(loadMoreButton);
    }

    public boolean areResultsPresent() {
        try {
            wait.forElementClickable(By.cssSelector(SEARCH_RESULT_SELECTOR), 0);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public SearchResultsPage clickLoadMoreButton() {
        Log.info("Click Load More button ");
        jsActions.scrollToElement(loadMoreButton);
        wait.forElementClickable(loadMoreButton);
        loadMoreButton.click();
        //NEEDTOCHECK
        wait.forElementVisibleW(loadMoreButton);
        return this;
    }

    public SearchResultsPage waitForResultsLoaded() {
        wait.forElementNotVisible(By.cssSelector(SPINNER_SELECTOR));
        return this;
    }

    public int getResultCardsNumber() {
        waitForResultsLoaded();
        return resultCards.size();
    }

    private boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            Log.info(e.getMessage());
            return false;
        }
    }
}

package com.automation.ui.connected.pageobjectsfactory.pageobject.search.intrawikisearch;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SearchPageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class IntraSiteSearchPageObject extends SearchPageObject {

    private static final String TITLES_CSS = ".Results article h1 .result-link";
    final private String photoExtension = ".jpg";
    final private String thumbnailsVideosGroup = ".Results a.image.video.lightbox";
    final private By jqueryAutocompleteBy = By.cssSelector("[src*='jquery.autocomplete']");
    @FindBy(css = ".photos-and-videos")
    private WebElement photosVideos;
    @FindBy(css = "#searchInput")
    private WebElement searchField;
    @FindBy(css = "#searchForm .search-submit")
    private WebElement searchButton;
    @FindBy(css = "[value=is_image]")
    private WebElement filterPhotos;
    @FindBy(css = "[value=is_video]")
    private WebElement filterVideos;
    @FindBy(css = "[name=rank]")
    private WebElement sortingOptions;
    @FindBy(css = TITLES_CSS)
    private List<WebElement> titles;
    @FindBy(css = ".Results article img")
    private List<WebElement> images;
    @FindBy(css = ".Results article a img")
    private List<WebElement> videoImages;
    @FindBy(css = ".Results article")
    private List<WebElement> descriptions;
    @FindBy(css = ".Results article li > a")
    private List<WebElement> urls;
    @FindBy(css = ".SearchInput .grid-1.alpha")
    private WebElement searchHeadline;
    @FindBy(css = ".search-tabs.grid-1.alpha")
    private WebElement searchTabs;
    @FindBy(css = "#advanced-link")
    private WebElement advancedButton;
    @FindBy(css = "#AdvancedSearch")
    private WebElement advancedField;
    @FindBy(css = ".top-wiki-articles.RailModule")
    private WebElement topModule;
    @FindBy(css = ".top-wiki-article-thumbnail")
    private List<WebElement> topModuleArticleThumbnail;
    @FindBy(css = ".top-wiki-article-text")
    private List<WebElement> topModuleArticleText;
    @FindBy(css = ".top-wiki-article.result")
    private List<WebElement> topModuleResults;
    @FindBy(css = "#AdvancedSearch label")
    private List<WebElement> advancedOptions;
    @FindBy(css = "#AdvancedSearch label input")
    private List<WebElement> advancedOptionInputs;
    @FindBy(css = ".Results .image")
    private List<WebElement> thumbnailsImages;
    @FindBy(css = ".Results a.image.video.lightbox")
    private List<WebElement> thumbnailsVideos;
    @FindBy(xpath = "//ul[contains(@id,'Autocomplete')]/li")
    private List<WebElement> suggestionsList;
    @FindBy(css = ".search-tabs.grid-1.alpha")
    private List<WebElement> filterOptions;
    @FindBy(css = ".play-circle")
    private List<WebElement> playMovieImages;
    @FindBy(css = ".result-description .result-link")
    private WebElement pushToTopWikiResult;
    @FindBy(css = ".wikiPromoteThumbnail")
    private WebElement pushToTopWikiThumbnail;
    @FindBy(css = ".search-suggest-img-wrapper")
    private List<WebElement> suggestionImagesList;
    @FindBy(css = "#WikiaSearchHeader .search-suggest li:not(.all)")
    private List<WebElement> newSuggestionsList;
    @FindBy(css = ".block")
    private List<WebElement> suggestionTextsList;
    @FindBy(id = "searchInput")
    private WebElement searchInputInGlobalNav;
    @FindBy(id = "searchForm")
    private WebElement searchFormInGlobalNav;

    public IntraSiteSearchPageObject(WebDriver driver) {
        super(driver);
    }

    /*
     * This method is checking whether text is translatable by adding "&uselang=qqx" to URl
     */
    public void addQqxUselang() {
        goToCurrentUrlWithSuffix(URLsContent.TRANSLATABLE_LANGUAGE);
    }

    public void searchFor(String query) {
        searchField.sendKeys(query + Keys.ENTER);
        Log.log("searchFor", "searching for query: " + query, true, driver);
    }

    public void verifySuggestions(String suggestion) {
        //NEEDTOCHECK
        wait.forElementVisibleW(suggestionsList.get(0));
        for (int i = 0; i < suggestionsList.size(); i++) {
            Assertion.assertStringContains(suggestionsList.get(i).getText(), suggestion);
        }
    }

    public void triggerSuggestions(String query) {
        searchField.click();
        wait.forElementPresent(jqueryAutocompleteBy);
        searchField.sendKeys(query);
        //NEEDTOCHECK
        wait.forElementVisibleW(suggestionsList.get(0));
    }

    private void verifyLanguageTranslation(WebElement element) {
        Assertion.assertTrue(element.getText().startsWith("("), "some phrases are not translatable");
        Assertion.assertTrue(element.getText().endsWith(")"), "some phrases are not translatable");
    }

    public void verifyLanguageTranslation() {
        for (int i = 0; i < filterOptions.size(); i++) {
            verifyLanguageTranslation(filterOptions.get(i));
        }
        verifyLanguageTranslation(resultCountMessage);
        verifyLanguageTranslation(searchHeadline);
        verifyLanguageTranslation(advancedButton);
    }

    public void verifyFirstResult(String query) {
        Assertion.assertStringContains(firstResult.getText(), query.replaceAll("_", " "));
        for (WebElement elem : descriptions) {
            Assertion.assertTrue(!elem.getText().isEmpty());
        }
        Assertion.assertEquals(titles.size(), urls.size());
    }

    public void verifyFirstResultExtension(String query) {
        Assertion.assertTrue(titles.get(0).getText().endsWith(query + photoExtension));
    }

    public void verifyLastResultPage() {
        wait.forElementClickable(paginationPages.get(paginationPages.size() - 1));
        do {
            //NEEDTOCHECK
            wait.forElementVisibleW(paginationPages.get(paginationPages.size() - 1));
            scrollAndClick(paginationPages.get(paginationPages.size() - 1));
        } while (paginationPages.size() > 6);
        wait.forElementVisible(By.cssSelector(PAGINATION_PAGES_CSS));
        Assertion.assertEquals(paginationPages.size(), 6);
        wait.forElementVisible(By.cssSelector(TITLES_CSS));
        Assertion.assertTrue(titles.size() <= 25);
    }

    public void verifyFirstArticleNameTheSame(String firstResult) {
        wait.forElementVisible(By.cssSelector(TITLES_CSS));
        Assertion.assertEquals(titles.get(0).getText().toLowerCase(), firstResult.toLowerCase());
    }

    public String getTitleInnerText() {
        return titles.get(0).getText();
    }

    public void verifyFirstArticleNameNotTheSame(String firstResult) {
        wait.forElementVisible(By.cssSelector(TITLES_CSS));
        Assertion.assertNotEquals(titles.get(0).getText(), firstResult);
    }

    public void verifyResultsCount(int i) {
        Assertion.assertNumber(titles.size(), i, "checking results count");
    }

    public void clickAdvancedButton() {
        advancedButton.click();
        Log.log("clickAdvancedButton", "Advance button was clicked", true, driver);
    }

    public void chooseAdvancedOption(int i) {
        //NEEDTOCHECK
        wait.forElementVisibleW(advancedField);
        advancedOptionInputs.get(i).click();
        Log
                .log("chooseAdvancedOption", "chosen advance option is selected", true, driver);
    }

    public void selectAllAdvancedOptions() {
        clickAdvancedButton();
        chooseAdvancedOption(0);
        Log.log("selectAllAdvancedOptions", "All advance options are selected", true,
                driver);
    }

    /*
     * Make sure namespace checkboxes are empty, except Articles and Category
     */
    public void verifyDefaultNamespaces() {
        //NEEDTOCHECK
        wait.forElementVisibleW(advancedField);
        for (int i = 0; i < advancedOptions.size(); i++) {

            String optionName = advancedOptions.get(i).getText();
            String optionState = advancedOptionInputs.get(i).getAttribute("checked");

            if ("Articles".equals(optionName) | "Category".equals(optionName)) {
                Assertion.assertEquals(optionState, "true");
            } else {
                Assertion.assertNull(optionState);
            }
        }
    }

    public void selectPhotosVideos() {
        //NEEDTOCHECK
        wait.forElementVisibleW(photosVideos);
        scrollAndClick(photosVideos);
        //NEEDTOCHECK
        wait.forElementVisibleW(sortingOptions);
        Log.log("selectPhotosVideos", "Photos and videos option is selected", true,
                driver);
    }

    public void verifyPhotosOnly() {
        //NEEDTOCHECK
        wait.forElementVisibleW(thumbnailsImages.get(0));
        wait.forElementNotPresent(By.cssSelector(thumbnailsVideosGroup));
        for (int i = 0; i < titles.size(); i++) {
            //NEEDTOCHECK
            wait.forElementVisibleW(titles.get(i));
            jsActions.scrollToElement(titles.get(i));
            //NEEDTOCHECK
            wait.forElementVisibleW(images.get(i));
        }
    }

    public void verifyVideosOnly() {
        //NEEDTOCHECK
        wait.forElementVisibleW(thumbnailsVideos.get(0));
        Assertion.assertTrue(thumbnailsVideos.size() == 25);
        // make sure there are as many videos as play buttons
        //NEEDTOCHECK
        wait.forElementVisibleW(playMovieImages.get(0));
        Assertion.assertEquals(playMovieImages.size(), thumbnailsVideos.size());
        for (int i = 0; i < titles.size(); i++) {
            //NEEDTOCHECK
            wait.forElementVisibleW(titles.get(i));
            jsActions.scrollToElement(titles.get(i));
            //NEEDTOCHECK
            wait.forElementVisibleW(videoImages.get(i));
        }
    }

    public void verifyNamespace(String namespace) {
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
        try {
            new WebDriverWait(driver, 30)
                    .until((ExpectedCondition<Boolean>) webDriver -> !titles.isEmpty());
        } finally {
            restoreDefaultImplicitWait();
        }

        Assertion.assertTrue(titles.get(0).getText().startsWith(namespace));
    }

    public void verifyTopModule() {
        //NEEDTOCHECK
        wait.forElementVisibleW(topModule);
        Assertion.assertNumber(topModuleResults.size(), 7, "Top module has correct amount of results");
        for (int i = 0; i < topModuleResults.size(); i++) {
            Assertion.assertTrue(topModuleArticleThumbnail.get(i).isDisplayed());
            Assertion.assertTrue(topModuleArticleText.get(i).isDisplayed());
        }
    }

    public void selectPhotosOnly() {
        scrollAndClick(filterPhotos);
        Log.log("selectPhotosOnly", "Photos option is selected", true, driver);
    }

    public void verifyAllResultsImages(int numberOfResults) {
        Assertion.assertEquals(numberOfResults, thumbnailsImages.size());
    }

    public void verifyAllResultsVideos(int numberOfResults) {
        Assertion.assertEquals(numberOfResults, thumbnailsVideos.size());
    }

    public void selectVideosOnly() {
        scrollAndClick(filterVideos);
        Log.log("selectVideosOnly", "Videos option is selected", true, driver);
    }

    public void verifyTitlesNotEmpty() {
        for (WebElement elem : titles) {
            Assertion.assertNotNull(elem.getText());
        }
    }

    public void sortBy(sortOptions option) {
        Select dropDown = new Select(sortingOptions);
        switch (option) {
            case RELEVANCY:
                dropDown.selectByIndex(0);
                break;
            case PUBLISH_DATE:
                dropDown.selectByIndex(1);
                break;
            case DURATION:
                dropDown.selectByIndex(2);
                break;
            default:
                throw new NoSuchElementException("Non-existing sort option selected");
        }
    }

    public List<String> getTitles() {
        return titles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void compareTitleListsNotEquals(List<String> titles1, List<String> titles2) {
        Assertion.assertNotEquals(titles1, titles2, "titles are the same");
    }

    public void verifyPushToTopWikiTitle(String searchWiki) {
        Assertion.assertStringContains(pushToTopWikiResult.getText(), searchWiki);
    }

    public void verifyPushToTopWikiThumbnail() {
        //NEEDTOCHECK
        wait.forElementVisibleW(pushToTopWikiThumbnail);
        Log.log("verifyPushToTopWikiThumbnail", "Push to top wiki thumbnail verified",
                true, driver);
    }

    public void verifyNewSuggestionsTextAndImages(String query) {
        searchField.click();
        wait.forElementPresent(jqueryAutocompleteBy);
        searchField.sendKeys(query);
        //NEEDTOCHECK
        wait.forElementVisibleW(newSuggestionsList.get(0));
        for (int i = 0; i < newSuggestionsList.size(); i++) {
            Assertion.assertStringContains(suggestionTextsList.get(i).getText(), query);
            Assertion.assertTrue(suggestionImagesList.get(i).isDisplayed());
        }
        Log.log("verifyNewSuggestionsTextAndImages",
                "Image and text next to every suggestion is verified", true);
    }

    public void searchForInGlobalNavIfPresent(String query) {
        if (isNewGlobalNavPresent()) {
            searchInputInGlobalNav.sendKeys(query);
            waitForValueToBePresentInElementsAttributeByElement(searchInputInGlobalNav, "value", query);
            searchFormInGlobalNav.submit();
        } else {
            searchField.sendKeys(query);
            searchButton.click();
        }
        Log.log("searchFor", "searching for query: " + query, true, driver);
    }

    public enum sortOptions {
        RELEVANCY, PUBLISH_DATE, DURATION;
    }


}
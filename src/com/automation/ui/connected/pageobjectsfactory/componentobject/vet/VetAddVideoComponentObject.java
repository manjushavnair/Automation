package com.automation.ui.connected.pageobjectsfactory.componentobject.vet;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VetAddVideoComponentObject extends SiteBasePageObject {

    @FindBy(css = "#VideoEmbedUrl")
    private WebElement urlField;
    @FindBy(css = "#VideoEmbedUrlSubmit")
    private WebElement addUrlButton;
    @FindBy(css = "#VET-suggestions .carousel li")
    private WebElement libraryLIs;
    @FindBy(css = "#VET-search-field")
    private WebElement findField;
    @FindBy(css = "#VET-search-submit")
    private WebElement findButton;
    @FindBys(@FindBy(css = "#VET-suggestions .carousel li"))
    private List<WebElement> videoList;
    @FindBys(@FindBy(css = "#VET-suggestions .video-thumbnail"))
    private List<WebElement> videoThumbnailsList;
    @FindBy(css = "#VET-video-wrapper .Wikia-video-enabledEmbedCode")
    private WebElement videoPlayer;
    @FindBy(css = ".video-thumbnail")
    private WebElement suggestedVideo;
    @FindBy(css = "a.bottom-close-button")
    private WebElement closeButton;
    @FindBy(css = "#VET-add-from-preview")
    private WebElement addFromPreviewButton;

    private By addFromPreviewButtonBy = By.cssSelector("#VET-add-from-preview");
    private By addVideoLibraryLink = By.cssSelector("figure + a");
    private By addVideoModalBy = By.cssSelector("#VideoEmbedBackWrapper");
    private By videoEmbedDetailsBy = By.cssSelector("#VideoEmbedDetails");

    private String videoName;

    public VetAddVideoComponentObject(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    private void typeInUrl(String url) {
        //NEEDTOCHECK
        wait.forElementVisibleW(urlField);
        urlField.sendKeys(url);
        Log.log("typeInUrl", url + " typed into url field", true);
    }

    private void clickAddButtonProvider() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addUrlButton);
        scrollAndClick(addUrlButton);
        Log.log("clickAddButton", "add url button clicked", true, driver);
    }

    private void typeInSearchQuery(String query) {
        //NEEDTOCHECK
        wait.forElementVisibleW(findField);
        findField.sendKeys(query);
        Log.log("typeInSearchQuery",
                query + " query typed into search video field", true);
    }

    private void clickFindButton() {
        //NEEDTOCHECK  //NEEDTOCHECK
        wait.forElementVisibleW(findButton);
        scrollAndClick(findButton);
        Log.log("clickFindButton", "find button clicked", true, driver);
    }

    private void clickAddVideoLibrary(int videoListItem) {
        WebElement listElem = videoList.get(videoListItem);
        //NEEDTOCHECK
        wait.forElementVisibleW(listElem);
        WebElement addVideoLink = listElem.findElement(addVideoLibraryLink);
        this.videoName = addVideoLink.getAttribute("title");
        wait.forElementClickable(addFromPreviewButtonBy);
        scrollAndClick(addFromPreviewButton);
        wait.forElementNotVisible(addFromPreviewButton);
        Log.log("clickAddVideoLibrary",
                "add video button clicked: " + this.videoName, true, driver);
    }

    private void checkIfLibraryIsPresent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(libraryLIs);
        Log.log("checkIfLibraryIsPresent", "library carousel present", true);
    }

    public VetOptionsComponentObject addVideoByUrl(String url) {
        typeInUrl(url);
        clickAddButtonProvider();
        wait.forElementVisible(videoEmbedDetailsBy);

        return new VetOptionsComponentObject(driver);
    }

    public VetOptionsComponentObject addVideoWithoutDetailsByUrl(String url) {
        typeInUrl(url);
        clickAddButtonProvider();
        wait.forElementNotVisible(addVideoModalBy);

        return new VetOptionsComponentObject(driver);
    }


    public VetOptionsComponentObject addVideoByQuery(String query, int i) {
        typeInSearchQuery(query);
        clickFindButton();
        checkIfLibraryIsPresent();
        clickVideoThumbnail(i);
        checkVideoPreviewAppearing();
        clickAddVideoLibrary(i);

        return new VetOptionsComponentObject(driver);
    }

    private void clickVideoThumbnail(int i) {
        scrollAndClick(videoThumbnailsList.get(i));
        Log.log("clickVideoThumbnail", "video thumbnails clicked", true);
    }

    private void checkVideoPreviewAppearing() {
        //NEEDTOCHECK
        wait.forElementVisibleW(videoPlayer);
        waitForValueToBePresentInElementsCssByCss("#VET-preview", "display", "block");
        Log.log("checkVideoPreviewAppearing", "video preview appeared", true);
    }

    public String getVideoName() {
        return this.videoName;
    }

    public boolean areSuggestionsDisplayed() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(suggestedVideo);

            return true;
        } catch (TimeoutException e) {
            Log.info("Suggestion are not displayed", e);

            return false;
        }
    }


}

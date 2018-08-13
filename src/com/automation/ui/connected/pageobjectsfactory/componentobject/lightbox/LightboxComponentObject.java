package com.automation.ui.connected.pageobjectsfactory.componentobject.lightbox;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LightboxComponentObject extends SiteBasePageObject {

    private static final Integer VIDEO_WIDTH_LIGHTBOX = 737;

    @FindBy(css = ".thumb.thumbinner")
    private WebElement imageThumbnail;
    @FindBy(css = "#LightboxModal")
    private WebElement lightBoxModal;
    @FindBy(css = "span.pin")
    private WebElement pinButton;
    @FindBy(css = "div.hero-inner img")
    private WebElement moreInfoThumbnail;
    @FindBy(css = "a.facebook")
    private WebElement facebookShareLink;
    @FindBy(css = "a.twitter")
    private WebElement twitterShareLink;
    @FindBy(css = "a.stumbleupon")
    private WebElement stumbleUponShareLink;
    @FindBy(css = "a.reddit")
    private WebElement redditShareLink;
    @FindBy(css = "a.plusone")
    private WebElement plusoneShareLink;
    @FindBy(css = ".video-media")
    private WebElement videoContainer;
    @FindBy(css = ".LightboxHeader h1 a")
    private WebElement titleLink;
    @FindBy(css = ".more-info-button")
    private WebElement moreInfoLink;
    @FindBy(css = ".WikiaLightbox div.media img")
    private WebElement imageContainer;
    @FindBy(css = "span.carousel-arrow.next")
    private WebElement carouselRight;
    @FindBy(css = "span.carousel-arrow.previous:not(.disabled)")
    private WebElement carouselLeft;
    @FindBy(css = "span.carousel-arrow.previous.disabled")
    private WebElement carouselLeftDisabled;
    @FindBy(css = "button.more-info-close")
    private WebElement closeShareScreenButton;

    public LightboxComponentObject(WebDriver driver) {
        super();
    }

    public void verifyLightboxPopup() {
        //NEEDTOCHECK
        wait.forElementVisible(lightBoxModal);
        Log.log("verifyLightboxPopup", "Lightbox appeared", true);
    }

    public void verifyLightboxVideo() {
        //NEEDTOCHECK
        wait.forElementVisible(videoContainer);
        Log.log("verifyLightboxVideo", "Lightbox video appeared", true);
    }

    public void verifyLightboxImage() {
        //NEEDTOCHECK
        wait.forElementVisible(imageContainer);
        Log.log("verifyLightboxImage", "Lightbox image appeared", true);
    }

    public LightboxComponentObject openLightbox() {
        //NEEDTOCHECK
        wait.forElementVisible(imageThumbnail);
        scrollAndClick(imageThumbnail);
        Log.log("openLightbox", "opened ligthbox", true);
        return new LightboxComponentObject(driver);
    }

    public void makeHeaderVisible() {
        //NEEDTOCHECK
        wait.forElementVisible(titleLink);
        jsActions.execute("$('.LightboxHeader').css('opacity', '1')");
    }


    public void clickPinButton() {
        builder.moveToElement(lightBoxModal).
                click(pinButton).
                build().
                perform();
        Log.log("clickPinButton", "pin button was clicked", true);
    }

   /* public void clickShareButton() {
        //NEEDTOCHECK
        wait.forElementVisible(shareButton);
        shareButton.click();
        //NEEDTOCHECK
        wait.forElementVisible(moreInfoThumbnail);
        Log.log("clickShareButton", "share button is clicked", true);
    }*/

    public void clickCloseShareScreenButton() {
        //NEEDTOCHECK
        wait.forElementVisible(closeShareScreenButton);
        closeShareScreenButton.click();
        Log
                .log("clickCloseShareScreenButton", "close share screen button was clicked", true);
    }

  /*  public void verifyShareScreenClosed() {
        waitForElementNotVisibleByElement(shareScreen);
        Log.log("verifyShareScreenClosed", "share screen is closed", true);
    }*/

    public void verifyLightboxClosed() {
        waitForElementNotVisibleByElement(lightBoxModal);
        Log.log("verifyShareScreenClosed", "share lightbox is closed", true);
    }

    public void verifyShareButtons() {
        //NEEDTOCHECK
        wait.forElementVisible(plusoneShareLink);
        wait.forElementVisible(redditShareLink);
        wait.forElementVisible(stumbleUponShareLink);
        wait.forElementVisible(twitterShareLink);
        wait.forElementVisible(facebookShareLink);
        Log.log("verifyShareButtons", "all share buttons are visible", true);
    }

    public void clickFacebookShareButton() {
        facebookShareLink.click();
        Log.log("clickFacebookShareButton", "fb share button is clicked", true);
    }

    public void clickTwitterShareButton() {
        twitterShareLink.click();
        Log.log("clickTwitterShareButton", "twitter share button is clicked", true);
    }

    public void clickStumbleUponShareButton() {
        stumbleUponShareLink.click();
        Log
                .log("clickStumbleUponShareButton", "stumbleupon share button is clicked", true);
    }

    public void clickRedditShareButton() {
        redditShareLink.click();
        Log.log("clickRedditShareButton", "reddit share button is clicked", true);
    }

    public void clickPlusOneShareButton() {
        plusoneShareLink.click();
        Log.log("clickPlusOneShareButton", "plus one share button is clicked", true);
    }


    public void clickCarouselRight() {
        //NEEDTOCHECK
        wait.forElementVisible(carouselRight);
        carouselRight.click();
        Log.log("clickCarouselRight", "carousel right button is clicked", true);
    }

    public void clickCarouselLeft() {
        //NEEDTOCHECK
        wait.forElementVisible(carouselLeft);
        carouselLeft.click();
        Log.log("clickCarouselLeft", "carousel left button is clicked", true);
    }

    public void verifyCarouselLeftDisabled() {
        //NEEDTOCHECK
        wait.forElementVisible(carouselLeftDisabled);
        Log.log("verifyCarouselLeftDisabled", "carousel left button is disabled", true);
    }


}

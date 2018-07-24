package com.automation.ui.connected.elements.oasis.components.articlepreview;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;

public class MobilePreviewModal extends SiteBasePageObject {

    public MobilePreviewModal() {
        super();
    }

    public MobilePreviewModal heroImageIsPresent() {
        waitForElementToBePresent(".has-hero-image", "Hero image");

        return this;
    }

    public MobilePreviewModal infoboxIsPresent() {
        waitForElementToBePresent(".portable-infobox", "Portable infobox");

        return this;
    }

    public MobilePreviewModal articleTableIsPresent() {
        waitForElementToBePresent(".article-table", "Article table");

        return this;
    }

    public MobilePreviewModal mediaGalleryIsPresent() {
        waitForElementToBePresent(".article-media-gallery", "Media gallery");

        return this;
    }

    public MobilePreviewModal linkedMediaGalleryIsPresent() {
        waitForElementToBePresent(".article-media-linked-gallery", "Linked media gallery");

        return this;
    }

    public MobilePreviewModal singleImageIsPresent() {
        waitForElementToBePresent("#Single_Image", "Single image");

        return this;
    }

    public MobilePreviewModal singleVideoIsPresent() {
        waitForElementToBePresent(".article-video", "Single video");

        return this;
    }

    private void waitForElementToBePresent(String cssSelector, String elementName) {
        Log.info("Waiting for: \"" + elementName + "\", to be present");
        wait.forElementPresent(By.cssSelector(cssSelector));
        Log.info("\"" + elementName + "\", is present");
    }
}

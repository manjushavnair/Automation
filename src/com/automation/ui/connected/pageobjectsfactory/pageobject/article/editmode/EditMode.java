package com.automation.ui.connected.pageobjectsfactory.pageobject.article.editmode;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditMode extends SiteBasePageObject {

    @FindBy(css = "#wpSave")
    private WebElement submitButton;

    @FindBy(css = "#wpPreview")
    private WebElement desktopPreviewButton;

    @FindBy(css = "a.RTEImageButton")
    private WebElement photoButton;

    @FindBy(css = "a.RTEVideoButton")
    private WebElement videoButton;

    @FindBy(css = "a.RTEGalleryButton")
    private WebElement galleryButton;

    @FindBy(css = "a.RTESlideshowButton")
    private WebElement slideshowButton;

    @FindBy(css = "a.RTESliderButton")
    private WebElement sliderButton;

    @FindBy(css = "a.cke_button_ModeWysiwyg > span.cke_button_label")
    private WebElement visualButton;

    @FindBy(css = "a.cke_button_ModeSource > span.cke_button_label")
    private WebElement sourceButton;

    @FindBy(css = "a.cke_button_off.cke_button_table")
    private WebElement addTableButton;

    @FindBy(css = ".editpage-notices")
    private WebElement notificationForAnon;

    private By submitButtonBy = By.cssSelector("#wpSave");

    public EditMode() {
        super();
    }

    private void submit() {
        driver.switchTo().defaultContent();
        wait.forElementClickable(submitButton);
        scrollAndClick(submitButton);

        Log.info("Submit");
    }


    /**
     * Submitting an edit is expecting a notification
     */
    public EditMode submitExpectingNotification() {
        submit();
        //NEEDTOCHECK
        wait.forElementVisibleW(notificationForAnon);
        Log.info("Notification is visible");

        return this;
    }

    public PreviewEditModePageObject previewArticle() {
        driver.switchTo().defaultContent();
        wait.forElementClickable(desktopPreviewButton);
        desktopPreviewButton.click();
        Log.log("preview", "Page preview displayed", true);
        return new PreviewEditModePageObject(driver);
    }


    public PhotoAddComponentObject clickPhotoButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(photoButton);
        scrollAndClick(photoButton);
        Log.log("clickPhotoButton", "photo button clicked", true);
        return new PhotoAddComponentObject(driver);
    }

    public VetAddVideoComponentObject clickVideoButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(videoButton);
        scrollAndClick(videoButton);
        Log.log("clickVideoButton", "video button clicked", true);
        return new VetAddVideoComponentObject(driver);
    }

    public TableBuilderComponentObject clickAddTableButton() {
        wait.forElementClickable(addTableButton).click();
        Log.log("addTable", "add table button clicked", true);
        return new TableBuilderComponentObject(driver);
    }

    public SliderBuilderComponentObject clickSliderButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(sliderButton);
        scrollAndClick(sliderButton);
        Log.log("clickSliderButton", "slider button clicked", true);
        return new SliderBuilderComponentObject(driver);
    }

    public SlideshowBuilderComponentObject clickSlideshowButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(slideshowButton);
        scrollAndClick(slideshowButton);
        Log.log("clickSlideshowButton", "slideshow button clicked", true);
        return new SlideshowBuilderComponentObject(driver);
    }

    public GalleryBuilderComponentObject clickGalleryButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(galleryButton);
        scrollAndClick(galleryButton);
        Log.log("clickGalleryButton", "gallery button clicked", true);
        return new GalleryBuilderComponentObject(driver);
    }

    public VisualEditModePageObject clickVisualButton() {
        visualButton.click();
        Log.log("clickVisualButton", "visual button clicked", true);
        return new VisualEditModePageObject();
    }

    public SourceEditModePageObject clickSourceButton() {
        sourceButton.click();
        Log.log("clickSourceButton", "source button clicked", true);
        return new SourceEditModePageObject();
    }
}

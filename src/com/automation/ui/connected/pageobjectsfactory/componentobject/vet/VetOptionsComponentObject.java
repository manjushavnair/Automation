package com.automation.ui.connected.pageobjectsfactory.componentobject.vet;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage.editmode.SiteArticleEditMode;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VetOptionsComponentObject extends AddMediaModalComponentObject {

    private static final int VIDEO_THUMBNAIL_WIDTH = 350;
    @FindBy(css = "#VideoEmbedLayoutRow")
    private WebElement videoEmbedLayotRow;
    @FindBy(css = "#VideoEmbedCaption")
    private WebElement captionField;
    @FindBy(css = "#VideoEmbedManualWidth")
    private WebElement widthInputField;
    @FindBy(css = "#VET_LayoutLeftBox label")
    private WebElement positionLayoutLeft;
    @FindBy(css = "#VET_LayoutCenterBox label")
    private WebElement positionLayoutCenter;
    @FindBy(css = "#VET_LayoutRightBox label")
    private WebElement positionLayoutRight;
    @FindBy(css = ".input-group.button-group input")
    private WebElement addAvideo;
    @FindBy(css = "#VideoEmbedCloseButton")
    private WebElement returnToEditing;
    @FindBy(css = "input.wikia-button.v-float-right")
    private WebElement updateVideoButton;
    @FindBy(css = "input#VideoEmbedName")
    private WebElement uneditableVideoNameField;
    @FindBy(css = "#VideoEmbedThumb .video-embed")
    private WebElement videoThumbnail;
    @FindBy(css = "div#VideoEmbedNameRow p")
    private WebElement videoNameCaption;

    public VetOptionsComponentObject(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public String getVideoName() {
        return videoNameCaption.getText();
    }

    public VetOptionsComponentObject setCaption(String caption) {
        //NEEDTOCHECK
        wait.forElementVisibleW(captionField);
        captionField.clear();
        captionField.sendKeys(caption);
        Log.log("setCaption", "caption was set to: " + caption, true);

        return this;
    }

    public void adjustWith(int width) {
        String widthString = Integer.toString(width);
        //NEEDTOCHECK
        wait.forElementVisibleW(widthInputField);
        widthInputField.clear();
        widthInputField.sendKeys(widthString);
        Log.log("adjustWith", "width set to: " + width, true, driver);
    }

    private void clickAddaVideo() {
        wait.forElementClickable(addAvideo);
        scrollAndClick(addAvideo);
        Log.log("clickAddaVideo", "add video button clicked", true, driver);
    }

    private void clickRetunToEditing() {
        //NEEDTOCHECK
        wait.forElementVisibleW(returnToEditing);
        scrollAndClick(returnToEditing);
        Log.log("clickReturnToEditing", "return to editing button clicked", true, driver);
    }

    private void verifyVideoThumbnail() {
        //NEEDTOCHECK
        wait.forElementVisibleW(videoThumbnail);
        Dimension dim = videoThumbnail.getSize();
        int w = dim.getWidth();
        Assertion.assertEquals(w, VIDEO_THUMBNAIL_WIDTH);
        Log.log("verifyVideoThumbnail", "video thumbnail is visible", true);
    }

    private void verifyVideoModalNotVisible() {
        waitForElementNotVisibleByElement(addVideoModal);
    }

    public SiteArticleEditMode submit() {
        verifyVideoThumbnail();
        clickAddaVideo();
        clickRetunToEditing();
        verifyVideoModalNotVisible();

        return new SiteArticleEditMode();
    }

    public void update() {
        clickAddaVideo();
        verifyVideoModalNotVisible();
    }

    public void adjustPosition(PositionsVideo position) {
        //NEEDTOCHECK
        wait.forElementVisibleW(videoEmbedLayotRow);
        switch (position) {
            case LEFT:
                positionLayoutLeft.click();
                break;
            case CENTER:
                positionLayoutCenter.click();
                break;
            case RIGHT:
                positionLayoutRight.click();
                break;
            default:
                throw new NoSuchElementException("Non-existing position selected");
        }
        Log.log("adjustPosition", "position " + position.toString() + " selected", true);
    }

    public void verifyVideoAlignmentSelected(PositionsVideo positions) {
        //NEEDTOCHECK
        wait.forElementVisibleW(videoEmbedLayotRow);
        String selectedPositionId = videoEmbedLayotRow
                .findElement(By.cssSelector(".selected"))
                .getAttribute("id");
        String desiredPositionId;
        switch (positions) {
            case LEFT:
                desiredPositionId = "VET_LayoutLeftBox";
                break;
            case CENTER:
                desiredPositionId = "VET_LayoutCenterBox";
                break;
            case RIGHT:
                desiredPositionId = "VET_LayoutRightBox";
                break;
            default:
                desiredPositionId = "desired position not provided";
        }
        Assertion.assertEquals(selectedPositionId, desiredPositionId);
    }

    public int getVideoWidth() {
        //NEEDTOCHECK
        wait.forElementVisibleW(widthInputField);

        return Integer.parseInt(widthInputField.getAttribute("value"));
    }

    public void verifyCaption(String captionDesired) {
        String caption = captionField.getAttribute("value");
        Assertion.assertEquals(caption, captionDesired);
    }

    public void verifyNameNotEditable() {
        Assertion.assertTrue(!uneditableVideoNameField.isDisplayed());
        Log.log("verifyNameNotEditable", "video name field not editable", true);
    }
}

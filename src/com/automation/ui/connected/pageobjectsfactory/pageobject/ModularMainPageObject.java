package com.automation.ui.connected.pageobjectsfactory.pageobject;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.CommonUtils;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.common.contentpatterns.PageContent;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModularMainPageObject extends SiteBasePageObject {

    @FindBy(css = ".hero-image")
    private WebElement heroImageModule;
    @FindBy(css = ".hero-description-text")
    private WebElement heroPublishedDescription;
    @FindBy(css = ".title-edit-btn")
    private WebElement editDescriptionButton;
    @FindBy(css = ".upload-group input")
    private WebElement updateCoverImageInput;
    @FindBy(css = ".image-save-bar .new-btn.save-btn")
    private WebElement imagePublishButton;
    @FindBy(css = ".hero-description .new-btn.save-btn")
    private WebElement descriptionPublishButton;
    @FindBy(css = ".hero-description .new-btn.discard-btn")
    private WebElement descriptionDiscardButton;
    @FindBy(css = ".edited-text")
    private WebElement descriptionEditField;
    @FindBy(css = ".edit-box")
    private WebElement editBox;
    @FindBy(css = ".position-text")
    private WebElement dragToRepositionText;
    @FindBy(css = ".update-btn")
    private WebElement updateCoverImageLink;
    @FindBy(css = "button.sg-sub[disabled=disabled]")
    private WebElement publishButtonDisabled;
    @FindBy(css = ".image-window")
    private WebElement imageWindowDragging;


    public ModularMainPageObject(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public void verifyMoMImage() {
        //NEEDTOCHECK
        wait.forElementVisibleW(heroImageModule);
        Log.log("verifyMoMImage", "Hero module image is visible", true);
    }

    public void clickUpdateCoverImageLink() {
        //NEEDTOCHECK
        wait.forElementVisibleW(updateCoverImageInput);
        updateCoverImageInput.click();
    }

    public void selectFileToUpload(String file) {
        updateCoverImageInput.sendKeys(CommonUtils.getAbsolutePathForFile(ClassLoader.getSystemResource(
                "ImagesForUploadTests/" + file).getPath()));
        Log.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
    }

    public void verifyDragToRepositionText() {
        //NEEDTOCHECK
        wait.forElementVisibleW(dragToRepositionText);
        Log.log("verifyDragToRepositionText",
                "Drag to reposition text message is visible", true);
    }

    public void clickPublishButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(imagePublishButton);
        imagePublishButton.click();
    }

    public void clickEditDescriptionLink() {
        //NEEDTOCHECK
        wait.forElementVisibleW(editDescriptionButton);
        editDescriptionButton.click();
    }

    public void typeMoMDescription(String momDescription) {
        //NEEDTOCHECK
        wait.forElementVisibleW(descriptionEditField);
        descriptionEditField.clear();
        descriptionEditField.sendKeys(momDescription);
        Log.log("typeMoMDescription", momDescription + "MoM description was typed in",
                true);
    }

    public void clickDescriptionPublishButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(descriptionPublishButton);
        descriptionPublishButton.click();
    }

    public void verifyEditedAndPublishedDescriptions(String editedDescription) {
        //NEEDTOCHECK
        wait.forElementVisibleW(heroPublishedDescription);
        Assertion.assertEquals(heroPublishedDescription.getText(), editedDescription);
    }

    public void verifyAdminStaffButtons() {
        //NEEDTOCHECK
        wait.forElementVisibleW(updateCoverImageLink);
        wait.forElementVisibleW(editDescriptionButton);
    }

    public void verifyNoAdminStaffButtons() {
        waitForElementNotVisibleByElement(updateCoverImageLink);
        waitForElementNotVisibleByElement(editDescriptionButton);
    }

    public String getMoMSrc() {
        //NEEDTOCHECK
        wait.forElementVisibleW(heroImageModule);
        return heroImageModule.getAttribute("src");
    }

    public void verifySrcTxtAreDifferent(String imgSrc, String newImgSrc) {
        //NEEDTOCHECK
        wait.forElementVisibleW(heroImageModule);
        Assertion.assertNotEquals(newImgSrc, imgSrc);
    }

    public void deleteDescriptionEditorContent() {
        descriptionEditField.clear();
        descriptionEditField.sendKeys("a");
        descriptionEditField.sendKeys(Keys.BACK_SPACE);
    }

    public void verifyWikiaPromotionalMessage() {
        String promoteMessage = descriptionEditField.getAttribute("placeholder");
        Assertion.assertEquals(promoteMessage, PageContent.WIKIA_HERO_PROMOTE_MESSAGE);
    }

    public void verifyPublishButtonDisability() {
        //NEEDTOCHECK
        wait.forElementVisibleW(editBox);
        wait.forElementVisibleW(publishButtonDisabled);
    }

    public String getDescriptionText() {
        //NEEDTOCHECK
        wait.forElementVisibleW(heroPublishedDescription);
        return heroPublishedDescription.getText();
    }

    public void addRandomTextToDescriptionField(String randomText) {
        //NEEDTOCHECK
        wait.forElementVisibleW(editBox);
        descriptionEditField.click();
        descriptionEditField.sendKeys(randomText);
    }

    public void verifyPublishedTextAndEditor(String publishedText) {
        //NEEDTOCHECK
        wait.forElementVisibleW(heroPublishedDescription);
        Assertion.assertEquals(heroPublishedDescription.getText(), publishedText);
    }

    public void clickDiscardButton() {
        wait.forElementVisibleW(descriptionDiscardButton);
        //NEEDTOCHECK
        wait.forElementClickable(descriptionDiscardButton);
        descriptionDiscardButton.click();
    }

    public void moveCoverImage() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(imageWindowDragging).clickAndHold().moveByOffset(-200, -200).release()
                .perform();
    }

    public String getTopAttribute() {
        if (StringUtils.isNotBlank(heroImageModule.getAttribute("style"))) {
            return heroImageModule.getAttribute("style");
        } else {
            return "";
        }
    }

    public void compareTopValues(String firstTopValue, String secondTopValue) {
        Assertion.assertNotEquals(secondTopValue, firstTopValue);
    }
}

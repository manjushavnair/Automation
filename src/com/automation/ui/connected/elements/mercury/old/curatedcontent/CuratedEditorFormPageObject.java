package com.automation.ui.connected.elements.mercury.old.curatedcontent;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.automation.ui.connected.elements.mercury.old.curatedcontent.imageupload.UploadImageModalComponentObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class CuratedEditorFormPageObject extends BasePageObject {

    @FindBy(css = "input#label")
    protected WebElement displayNameField;
    @FindBy(css = "input#title")
    protected WebElement pageNameField;

    @FindBy(css = ".sub-head--done")
    protected WebElement doneButton;

    @FindBy(css = ".curated-content-editor-remove")
    protected WebElement deleteItemButton;
    @FindBy(css = ".curated-content-editor-photo")
    protected WebElement imageField;

    public ItemFormPageObject clickDoneButton() {
        waitForPageReload();
        //NEEDTOCHECK
        wait.forElementVisibleW(doneButton);
        doneButton.click();
        waitForDeleteButtonToBeVisible();

        return new ItemFormPageObject();
    }

    public UploadImageModalComponentObject clickOnImage() {
        //NEEDTOCHECK
        wait.forElementVisibleW(imageField);
        imageField.click();

        return new UploadImageModalComponentObject(driver);
    }

    public CuratedEditorFormPageObject typeDisplayName(String displayName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(displayNameField);
        displayNameField.sendKeys(displayName);

        return this;
    }

    public CuratedEditorFormPageObject typePageName(String pageName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(pageNameField);
        pageNameField.sendKeys(pageName);

        return this;
    }

    private void waitForDeleteButtonToBeVisible() {
        //NEEDTOCHECK
        wait.forElementVisibleW(deleteItemButton);
    }
}

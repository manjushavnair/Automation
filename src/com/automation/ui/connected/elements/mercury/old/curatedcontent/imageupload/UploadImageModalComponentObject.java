package com.automation.ui.connected.elements.mercury.old.curatedcontent.imageupload;

import com.automation.ui.base.common.core.element.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadImageModalComponentObject {

    @FindBy(css = ".modal-dialog li:nth-of-type(3)")
    private WebElement searchForImageButton;
    @FindBy(css = ".modal-dialog-wrapper.menu .modal-dialog")
    private WebElement modal;
    @FindBy(css = "#fileUpload")
    private WebElement uploadInput;
    @FindBy(css = ".modal-dialog .crop-image")
    private WebElement cropImageButton;

    private Wait wait;
    private WebDriver driver;

    public UploadImageModalComponentObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);

        PageFactory.initElements(driver, this);
    }

    public SearchForImagePageObject clickSearchForImageButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(searchForImageButton);
        searchForImageButton.click();

        return new SearchForImagePageObject(driver);
    }

    public CroppingToolPageObject uploadImage(String filePath) {
        //NEEDTOCHECK
        wait.forElementVisibleW(modal);
        uploadInput.sendKeys(filePath);
        uploadInput.submit();
        return new CroppingToolPageObject(driver);
    }

    public boolean isCropOptionEnabled() {
        return !cropImageButton.getAttribute("class").contains("disabled");
    }

    public CroppingToolPageObject selectCrop() {
        //NEEDTOCHECK
        wait.forElementVisibleW(cropImageButton);
        cropImageButton.click();

        return new CroppingToolPageObject(driver);
    }
}

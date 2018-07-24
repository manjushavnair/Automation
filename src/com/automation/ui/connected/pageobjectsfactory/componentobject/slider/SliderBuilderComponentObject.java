package com.automation.ui.connected.pageobjectsfactory.componentobject.slider;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SliderBuilderComponentObject extends BasePageObject {

    @FindBy(css = "#WikiaPhotoGallerySliderType_bottom")
    private WebElement hPosition;
    @FindBy(css = "#WikiaPhotoGallerySliderType_right")
    private WebElement vPosition;
    @FindBy(css = "#WikiaPhotoGallerySliderAddImage")
    private WebElement addPhotoButton;
    @FindBy(css = "#WikiaPhotoGalleryEditorSave")
    private WebElement finishButton;

    public SliderBuilderComponentObject(WebDriver driver) {
        super();
        // TODO Auto-generated constructor stub
    }

    public void selectMenuPosition(MenuPositions pos) {
        //NEEDTOCHECK
        wait.forElementVisibleW(hPosition);
        wait.forElementVisibleW(vPosition);
        switch (pos) {
            case HORIZONTAL:
                hPosition.click();
                break;
            case VERTICAL:
                vPosition.click();
                break;
            default:
                throw new NoSuchElementException("Non-existing position selected");
        }
        Log
                .log("selectMenuPosition", pos.toString() + " position selected", true, driver);
    }

    public AddPhotoComponentObject clickAddPhoto() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addPhotoButton);
        addPhotoButton.click();
        Log.log("addPhoto", "add photo button clicked", true);
        return new AddPhotoComponentObject(driver);
    }

    public void clickFinish() {
        //NEEDTOCHECK
        wait.forElementVisibleW(finishButton);
        finishButton.click();
        Log.log("clickFinish", "finish button clicked", true);
    }

    public enum MenuPositions {
        HORIZONTAL, VERTICAL
    }

}

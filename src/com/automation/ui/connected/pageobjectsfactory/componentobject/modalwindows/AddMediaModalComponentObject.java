package com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddMediaModalComponentObject extends SiteBasePageObject {

    @FindBy(css = "#VideoEmbedBackWrapper")
    protected WebElement addVideoModal;
    @FindBy(css = "#UploadPhotosWrapper")
    private WebElement addPhotoModal;
    @FindBy(css = "#UploadPhotosWrapper .close")
    private WebElement modalAddPhotoClose;
    @FindBy(css = "#VideoEmbedBackWrapper button.close")
    private WebElement modalAddVideoClose;

    public void closeAddPhotoModal() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addPhotoModal);
        Log.log(
                "UploadPhotoModalIsPresent",
                "Upload photo modal is present",
                true, driver
        );
        scrollAndClick(modalAddPhotoClose);
        waitForElementNotVisibleByElement(addPhotoModal);
        Log.log(
                "UploadPhotoModalClosed",
                "Upload photo modal is closed",
                true, driver
        );
    }

    public void closeAddVideoModal() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addVideoModal);
        Log.log(
                "UploadVideoModalIsPresent",
                "Upload video modal is present",
                true, driver
        );
        scrollAndClick(modalAddVideoClose);
        waitForElementNotVisibleByElement(addVideoModal);
        Log.log(
                "UploadVideoModalClosed",
                "Upload video modal is closed",
                true, driver
        );
    }

    public boolean isVideoModalVisible() {
        //NEEDTOCHECK
        wait.forElementVisibleW(addVideoModal);

        return true;
    }
}

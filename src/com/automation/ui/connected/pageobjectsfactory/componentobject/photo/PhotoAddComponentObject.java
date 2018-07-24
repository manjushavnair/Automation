package com.automation.ui.connected.pageobjectsfactory.componentobject.photo;

import com.automation.ui.base.common.constants.SiteConstants;
import com.automation.ui.base.common.core.CommonUtils;
import com.automation.ui.base.common.core.interactions.Elements;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PhotoAddComponentObject extends BasePageObject {

    @FindBy(css = "#ImageQuery")
    private WebElement searchField;
    @FindBy(css = "#ImageUploadFind [value='Find']")
    private WebElement findButton;
    @FindBy(css = "#WMU_source_1")
    private WebElement flickButton;
    @FindBy(css = "#WMU_source_0")
    private WebElement thisWikiButton;
    @FindBy(css = "#ImageUploadFile")
    private WebElement chooseFileInput;
    @FindBy(css = "#ImageUploadForm input:nth-child(2)")
    private WebElement uploadButton;
    @FindBy(css = "tr.ImageUploadFindImages td a")
    private List<WebElement> addThisPhotoList;

    private String photoName;

    public PhotoAddComponentObject(WebDriver driver) {
        super();
    }

    public void verifyAddPhotoModal() {
        //NEEDTOCHECK
        wait.forElementVisibleW(searchField);
        wait.forElementVisibleW(findButton);
    }

    public void typeSearchQuery(String photoName) {
        //NEEDTOCHECK

        wait.forElementVisibleW(searchField);
        searchField.sendKeys(photoName);
        Log.log("typeSearchQuery", photoName + " searching", true);
    }

    public void clickFind() {
        //NEEDTOCHECK
        wait.forElementVisibleW(findButton);
        scrollAndClick(findButton);
        wait.forElementNotVisible(By.cssSelector("#ImageUploadProgress2"));
        Log.log("clickSearch", "search button clicked", true);
    }

    public PhotoOptionsComponentObject clickAddThisPhoto(int photoNumber) {
        //NEEDTOCHECK
        WebElement photo = wait.forElementVisibleW(addThisPhotoList.get(photoNumber));
        photoName =
                addThisPhotoList.get(photoNumber).findElement(By.cssSelector("img"))
                        .getAttribute("data-image-name");
        scrollAndClick(photo);
        Log.log("clickAddPhoto", "add photo button clicked", true);
        return new PhotoOptionsComponentObject(driver);
    }

    public PhotoOptionsComponentObject clickAddThisPhoto(String fileName) {
        WebElement photo = Elements.getElementByValue(addThisPhotoList, "title", fileName);
        photoName = photo.findElement(By.cssSelector("img")).getAttribute("data-image-name");
        scrollAndClick(photo);
        Log.log("clickAddPhoto", "add photo button clicked", true);
        return new PhotoOptionsComponentObject(driver);
    }

    public String getPhotoName() {
        return photoName.replace(' ', '_');
    }

    /**
     * Adding photo with given @photoName and @photoNumber
     */
    public PhotoOptionsComponentObject addPhotoFromWiki(String photoName, int photoNumber) {
        typeSearchQuery(photoName);
        clickFind();
        clickAddThisPhoto(photoNumber);
        return new PhotoOptionsComponentObject(driver);
    }

    public PhotoOptionsComponentObject addPhotoFromWiki(String photoName) {
        typeSearchQuery(photoName);
        clickFind();
        clickAddThisPhoto(photoName);
        return new PhotoOptionsComponentObject(driver);
    }

    public void clickThisWiki() {
        thisWikiButton.click();
        Log.log("clickThisWiki", "this wiki button clicked", true);
    }

    public void clickFlickr() {
        flickButton.click();
        Log.log("clickFlickr", "flickr button clicked", true);
    }

    public void chooseFileToUpload(String file) {
        chooseFileInput
                .sendKeys(
                        CommonUtils.getAbsolutePathForFile(SiteConstants.IMAGE_UPLOAD_RESOURCES_PATH + file));
        Log.log("selectFileToUpload", "select file " + file + " to upload it", true);
    }

    public void clickUpload() {
        uploadButton.click();
        Log.log("clickUpload", "click on upload button", true);
    }
}

package com.automation.ui.connected.pageobjectsfactory.pageobject.special.themedesigner;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.CommonUtils;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SpecialThemeDesignerPageObject extends SiteBasePageObject {

    String tabSelector = "a[rel='%tabName%Tab']";
    String selectedTabSelector = "li.selected a[rel='%tabName%Tab']";
    @FindBy(css = ".save:not([disabled=true])")
    private WebElement saveButton;
    @FindBy(css = ".save[disabled]")
    private WebElement saveButtonDisabled;
    // theme tab
    @FindBys(@FindBy(css = "li[data-theme]"))
    private List<WebElement> themes;
    @FindBy(css = ".next.chevron")
    private WebElement nextButton;
    // customize tab
    @FindBy(css = ".color-body")
    private WebElement bgColor;
    @FindBy(css = ".background-image")
    private WebElement bgImage;
    @FindBy(css = "#tile-background")
    private WebElement bgTileOption;
    @FindBy(css = "#fix-background")
    private WebElement bgFixOption;
    @FindBy(css = "#not-split-background")
    private WebElement bgNoSplitOption;
    @FindBy(css = ".ThemeDesignerPicker.image")
    private WebElement bgImagePicker;
    @FindBy(css = ".color-buttons")
    private WebElement pgButtons;
    @FindBy(css = ".color-links")
    private WebElement pgLinks;
    @FindBy(css = ".color-header")
    private WebElement pgHeader;
    @FindBy(css = ".color-page")
    private WebElement pgColor;
    @FindBy(css = ".page h1")
    private WebElement pgSectionTitle;
    // wordmark tab
    @FindBy(css = "#WordMarkUploadForm [value=Upload]")
    private WebElement wordmarkSubmit;
    @FindBy(css = "#WordMarkUploadFile")
    private WebElement wordmarkUpload;
    @FindBy(css = "#FaviconUploadForm [value=Upload]")
    private WebElement faviconSubmit;
    @FindBy(css = "#FaviconUploadFile")
    private WebElement faviconUpload;
    @FindBy(css = "ul[style='margin-left: -760px;']")
    private WebElement secondThemesSet;
    @FindBy(css = "ul[style='margin-left: -1520px;']")
    private WebElement thirdThemesSet;
    @FindBy(id = "backgroundImageUploadFile")
    private WebElement fileUploadInput;
    @FindBy(css = "#BackgroundImageForm [type='submit']")
    private WebElement imageSubmit;

    public SpecialThemeDesignerPageObject open() {
        getUrl(urlBuilder.getUrlForPage(URLsContent.SPECIAL_THEME_DESIGNER));

        return this;
    }

    /**
     * select theme on Special:ThemeDesigner page
     */
    public String selectTheme(int number) {
        wait.forElementClickable(themes.get(0));
        if (number < 5) {
            scrollAndClick(themes.get(number));
        }
        if (number >= 5 && number < 10) {
            scrollAndClick(nextButton);
            wait.forElementClickable(secondThemesSet);
            scrollAndClick(themes, number);
        }
        if (number == 10) {
            scrollAndClick(nextButton);
            wait.forElementClickable(secondThemesSet);
            scrollAndClick(nextButton);
            wait.forElementClickable(thirdThemesSet);
            scrollAndClick(themes, number);
        }
        String themeName =
                themes.get(number).getAttribute("data-theme").toLowerCase();
        Log.log("selectTheme", "theme " + themeName + " selected", true);
        return themeName;
    }

    public void verifyThemeSelected(String themeName) {
        wait.forElementVisible(By.cssSelector("li.selected[data-theme='" + themeName + "']"));
        Assertion.assertEquals((String) jsActions.execute("ThemeDesigner.settings.theme"), themeName);
        Log.log("verifyThemeSelected", "theme " + themeName + " selection verified",
                true);
    }

    public void submitTheme() {
        wait.forElementClickable(saveButton);
        scrollAndClick(saveButton);
        //NEEDTOCHECK
        wait.forElementVisibleW(saveButtonDisabled);
        Log.log("submitSelection", "selection of new skin saved", true);
    }

    public void uploadLargeImage() {
        fileUploadInput.sendKeys(CommonUtils.getAbsolutePathForFile(
                ClassLoader.getSystemResource("ImagesForUploadTests/2000x150.png").getPath()));
        imageSubmit.click();
    }

    public void selectTab(Tab tabName) {
        WebElement tab = wait
                .forElementVisible(By.cssSelector(tabSelector.replace("%tabName%", tabName.toString())));
        scrollAndClick(tab);
        wait.forElementVisible(
                By.cssSelector(selectedTabSelector.replace("%tabName%", tabName.toString())));
        Log.log("selectTab", tabName.toString() + " tab has been selected", true);
    }

    /**
     * no split option appears only for backgrounds that are over 2000px wide
     */
    public void verifyCustomizeTab() {
        //NEEDTOCHECK
        wait.forElementVisibleW(bgColor);
        wait.forElementVisibleW(bgImage);
        wait.forElementVisibleW(bgImage);
        wait.forElementVisibleW(bgTileOption);
        wait.forElementVisibleW(bgFixOption);
        wait.forElementVisibleW(bgNoSplitOption);
        wait.forElementVisibleW(pgButtons);
        wait.forElementVisibleW(pgLinks);
        wait.forElementVisibleW(pgHeader);
        wait.forElementVisibleW(pgColor);
    }

    public void verifyWordmarkTab() {
        //NEEDTOCHECK
        wait.forElementVisibleW(wordmarkSubmit);
        wait.forElementVisibleW(wordmarkUpload);
        wait.forElementVisibleW(faviconSubmit);
        wait.forElementVisibleW(faviconUpload);
    }

    public void openImagePicker() {
        //NEEDTOCHECK
        wait.forElementVisibleW(bgImage);
        bgImage.click();
        //NEEDTOCHECK
        wait.forElementVisibleW(bgImagePicker);
        Log.log("openImagePicker", "image picker opened", true, driver);
    }

    public void clickOutsideImagePicker() {
        //NEEDTOCHECK
        wait.forElementVisibleW(pgSectionTitle);
        pgSectionTitle.click();
        Log.log("clickOutsideImageSelectionDialog", "clicked outside Image Picker", true);
    }

    public void verifyImagePickerDisappeared() {
        waitForElementNotVisibleByElement(bgImagePicker);
        Log.log("verifyImagePickerDisappeared", "Image Picker is invisible", true);
    }

    public enum Tab {
        THEME, CUSTOMIZE, WORDMARK
    }

}

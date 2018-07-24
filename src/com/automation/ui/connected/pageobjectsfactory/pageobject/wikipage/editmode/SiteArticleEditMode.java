package com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage.editmode;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SiteArticleEditMode extends SiteEditMode {

    @FindBy(css = "a.RTEVideoButton")
    private WebElement videoButton;
    @FindBy(css = ".cke_source")
    private WebElement sourceModeTextArea;
    @FindBy(css = "div.cke_wrapper.cke_ltr div.cke_contents iframe")
    private WebElement iFrame;
    @FindBy(css = "span.cke_button_ModeSource a span.cke_label")
    private WebElement sourceModeButton;
    @FindBy(css = "span.cke_button_ModeWysiwyg a")
    private WebElement visualModeButton;
    @FindBy(css = "body[id='bodyContent']")
    private WebElement bodyContent;
    @FindBy(css = "#wpSave")
    private WebElement publishButton;
    @FindBy(css = "span[id=cke_22_label]")
    private WebElement sourceButton;


    public SourceEditModePageObject clickOnSourceButton() {
        //NEEDTOCHECKwait.forElementVisibleW(sourceModeButton);
        wait.forElementClickable(sourceModeButton);
        scrollAndClick(sourceModeButton);
        //NEEDTOCHECK
        wait.forElementVisibleW(sourceModeTextArea);
        Log.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
        return new SourceEditModePageObject();
    }

    public void clickOnVisualButton() {
        //NEEDTOCHECK
        //
        wait.forElementVisibleW(visualModeButton);
        wait.forElementClickable(visualModeButton);
        scrollAndClick(visualModeButton);
        //NEEDTOCHECK /n wait.forElementVisibleW(iFrame);
        Log.log("ClickOnVisualButton", "Click on 'Visual' button", true);
    }

    public void clickOnPublish() {
        wait.forElementClickable(publishButton);
        publishButton.click();
        Log.log("clickOnPublish", "publish button clicked", true, driver);
    }

    public void verifySourceEditorContentIsEmpty() {
        //NEEDTOCHECK /n
        wait.forElementVisibleW(sourceModeTextArea);
        Assertion.assertEquals(sourceModeTextArea.getText().isEmpty(), true);
        Log.log("verifySourceEditorContentIsEmpty", "Source editor content was cleaned",
                true);
    }

    public void clearSource() {
        //NEEDTOCHECK
        wait.forElementVisibleW(sourceModeTextArea);
        sourceModeTextArea.clear();
        Log.log("deleteArticleContent", "Delete all source code on the article", true);
    }

    public void typeInContent(String content) {
        //NEEDTOCHECK
        wait.forElementVisibleW(iFrame);
        driver.switchTo().frame(iFrame);
        //NEEDTOCHECK
        wait.forElementVisibleW(bodyContent);
        bodyContent.sendKeys(content);
        Log.log("typeInContent",
                "content " + bodyContent.getText() + " - type into article body", true, driver);
        driver.switchTo().defaultContent();
    }

    public void clickSourceButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(sourceButton);
        sourceButton.click();
        driver.switchTo().defaultContent();
        Log.log("clickSourceButton", "Source button was clicked", true, driver);
    }

    public SiteArticleEditMode editArticleByName(String name, String wikiUrl) {
        String newUrl = URLsContent.ADD_ARTICLE.replace("%title%", name);
        getUrl(wikiUrl + newUrl);
        return new SiteArticleEditMode();
    }

    public void typeContentInSourceMode(String content) {
        //NEEDTOCHECK
        wait.forElementVisibleW(sourceModeTextArea);
        sourceModeTextArea.sendKeys(content);
        Log.log("typeInContent", "content type into source mode textarea", true, driver);
    }
}

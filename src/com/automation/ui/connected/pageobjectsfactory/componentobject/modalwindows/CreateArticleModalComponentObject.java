package com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.common.contentpatterns.PageContent;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateArticleModalComponentObject extends SiteBasePageObject {

    @FindBy(css = "#wpCreatePageDialogTitle")
    private WebElement titleInput;
    @FindBy(css = "#CreatePageDialogToplist")
    private WebElement topListRadioButton;
    @FindBy(css = "#CreatePageDialogFormat")
    private WebElement standardRadioButton;
    @FindBy(css = "#CreatePageDialogBlank")
    private WebElement blankRadioButton;
    @FindBy(css = ".button.normal.primary")
    private WebElement createPageButton;
    @FindBy(css = "#createPageErrorMsg")
    private WebElement phalanxBlockMessageContainer;
    @FindBy(css = "button[data-event=create]")
    private WebElement addAPageButton;
    @FindBy(css = "#CreatePageModalDialog .close")
    private WebElement closeButton;

    public CreateArticleModalComponentObject(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public void createPageWithBlankLayout(String title) {
        createPage(title, "blank");
    }

    private void createPage(String title, String layout) {
        //NEEDTOCHECK
        wait.forElementVisibleW(titleInput);
        titleInput.sendKeys(title);
        chooseLayout(layout);
        //NEEDTOCHECK
        wait.forElementVisibleW(createPageButton);
        scrollAndClick(createPageButton);
        Log.log(
                "PageCreated",
                "Page with given title created",
                true
        );
    }

    public void verifyMessageAboutBlockPresent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(phalanxBlockMessageContainer);
        wait.forTextInElement(
                phalanxBlockMessageContainer, PageContent.PHALANX_BLOCK_TITLE_MESSAGE
        );
        Log.log(
                "MessageAboutBlockPresent",
                "Message about block present",
                true,
                driver
        );
    }

    /**
     * Checks layout's radiobutton accroding to layout type given as param Layout can have values:
     * standard - layout with video and image placeholders top - layout for top10List page blank -
     * blank page's layout
     */
    private void chooseLayout(String layout) {
        if ("standard".equals(layout)) {
            wait.forElementClickable(standardRadioButton);
            scrollAndClick(standardRadioButton);
            return;
        }
        if ("blank".equals(layout)) {
            wait.forElementClickable(blankRadioButton);
            scrollAndClick(blankRadioButton);
            return;
        }
        if ("top".equals(layout)) {
            wait.forElementClickable(topListRadioButton);
            scrollAndClick(topListRadioButton);
        }
    }

    public boolean isCreateNewArticleModalVisible() {
        try {
            //NEEDTOCHECK
            wait.forElementVisibleW(addAPageButton);
            return true;
        } catch (TimeoutException e) {
            Log.info(e.getMessage());
            return false;
        }
    }

    public void close() {
        wait.forElementClickable(closeButton).click();
    }
}

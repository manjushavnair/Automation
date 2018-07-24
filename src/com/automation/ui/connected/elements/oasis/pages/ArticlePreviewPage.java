package com.automation.ui.connected.elements.oasis.pages;

import com.automation.ui.base.pageobjectsfactory.pageobject.Navigate;
import com.automation.ui.connected.elements.oasis.components.articlepreview.MobilePreviewModal;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePreviewPage extends SiteBasePageObject {

    private static final String ARTICLE_PREVIEW_PAGE = "/wiki/ArticlePreview";
    private static final String[] EDIT_QUERY_PARAM = {"action=edit"};
    @FindBy(css = "#wpPreviewMobile")
    private WebElement mobilePreviewButton;
    @FindBy(css = ".mobile-preview")
    private WebElement mobilePreviewModal;
    @FindBy(css = ".mobile-preview iframe")
    private WebElement mobilePreviewIframe;
    private Navigate navigate;

    public ArticlePreviewPage() {
        super();

        navigate = new Navigate();
    }

    public ArticlePreviewPage navigateToArticlePreviewPageInEditMode() {
        navigate.toPageByPath(ARTICLE_PREVIEW_PAGE, EDIT_QUERY_PARAM);

        return this;
    }

    public MobilePreviewModal clickOnMobilePreviewButton() {
        wait.forElementClickable(mobilePreviewButton);
        mobilePreviewButton.click();
//NEEDTOCHECK
        wait.forElementVisibleW(mobilePreviewModal);
        driver.switchTo().frame(mobilePreviewIframe);

        return new MobilePreviewModal();
    }
}

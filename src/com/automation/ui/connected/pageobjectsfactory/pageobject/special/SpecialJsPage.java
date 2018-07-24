package com.automation.ui.connected.pageobjectsfactory.pageobject.special;

import com.automation.ui.base.common.core.TestContext;
import com.automation.ui.connected.pageobjectsfactory.componentobject.ContentReviewModule;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ludwik on 2015-09-25.
 */
public class SpecialJsPage extends SiteBasePageObject {

    @FindBy(css = ".source-javascript")
    private WebElement scriptArea;

    private ContentReviewModule contentReviewModule;

    public SpecialJsPage() {
        super();
    }

    /**
     * Open article with name that is the following combination: TEST CLASS NAME + TEST METHOD NAME
     */
    public SpecialJsPage open() {
        getUrl(urlBuilder.getUrlForPage(
                String.format("mediawiki:%s.js", TestContext.getCurrentMethodName())));

        return this;
    }

    public SpecialJsPage open(String articleTitle) {
        getUrl(urlBuilder.getUrlForPage(String.format("mediawiki:%s.js", articleTitle)));

        return this;
    }

    public String getScriptContent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(scriptArea);

        return scriptArea.getText();
    }

    public ContentReviewModule getReviewModule() {
        if (contentReviewModule == null) {
            contentReviewModule = new ContentReviewModule();
        }
        return contentReviewModule;
    }
}

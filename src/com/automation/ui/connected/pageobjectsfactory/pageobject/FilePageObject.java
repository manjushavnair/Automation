package com.automation.ui.connected.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilePageObject extends SiteBasePageObject {

    @FindBy(css = ".file-usage")
    private WebElement snippetSection;

    @FindBy(css = ".wikia-card__title")
    private WebElement snippetTitle;

    @FindBy(css = ".wikia-card__snippet")
    private WebElement snippetText;

    public String snippetText() {
        return snippetText.getText();
    }

    public boolean doesSnippetContainXSS() {
        //NEEDTOCHECK
        wait.forElementVisibleW(snippetSection);
        scrollTo(snippetTitle);
        return snippetText().contains("alert('xss')");
    }
}

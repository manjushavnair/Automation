package com.automation.ui.connected.pageobjectsfactory.pageobject.article;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePurger extends BasePageObject {

    @FindBy(css = ".mw-htmlform-submit")
    private WebElement confirmationButton;

    public ArticlePurger() {
        super();
    }

    public void purgeArticleAsLoggedUser() {
        driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), "action=purge"));
    }

    public void purgeArticleAsAnon() {
        driver.get(urlBuilder.appendQueryStringToURL(driver.getCurrentUrl(), "action=purge"));
        //NEEDTOCHECK
        wait.forElementVisibleW(confirmationButton);
        confirmationButton.click();
    }
}

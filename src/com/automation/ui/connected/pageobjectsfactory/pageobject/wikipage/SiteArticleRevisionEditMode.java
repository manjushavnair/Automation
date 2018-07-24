package com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage;

import com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage.editmode.SiteArticleEditMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SiteArticleRevisionEditMode extends SiteArticleEditMode {

    public SiteArticleRevisionEditMode(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

}

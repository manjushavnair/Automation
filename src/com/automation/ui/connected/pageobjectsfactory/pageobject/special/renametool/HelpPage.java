package com.automation.ui.connected.pageobjectsfactory.pageobject.special.renametool;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HelpPage extends SiteBasePageObject {


    private static final By HELP_PAGE_HEADER_BY = By.cssSelector(".page-header__title");


    public HelpPage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }
}

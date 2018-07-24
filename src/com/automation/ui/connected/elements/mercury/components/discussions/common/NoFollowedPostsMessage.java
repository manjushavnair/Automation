package com.automation.ui.connected.elements.mercury.components.discussions.common;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NoFollowedPostsMessage extends BasePageObject {
    @FindBy(css = ".no-followed-posts > h6")
    private WebElement header;

    @FindBy(css = ".no-followed-posts > div")
    private WebElement content;

    @FindBy(css = ".no-followed-posts > a")
    private WebElement button;

    public String getHeaderText() {
        return header.getText();
    }

    public String getContentText() {
        return content.getText();
    }

    public String getButtonText() {
        return button.getText();
    }
}

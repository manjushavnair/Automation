package com.automation.ui.connected.elements.mercury.components;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.notifications.Notifications;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfile extends BasePageObject {

    @Getter(lazy = true)
    private final Notifications notifications = new Notifications();
    @FindBy(css = ".wds-sign-out__button")
    private WebElement logoutButton;

    public boolean isLogoutButtonVisible() {
        //NEEDTOCHECK
        return wait.forElementVisibleW(logoutButton).isDisplayed();
    }

}

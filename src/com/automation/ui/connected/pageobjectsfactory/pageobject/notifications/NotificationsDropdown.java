package com.automation.ui.connected.pageobjectsfactory.pageobject.notifications;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationsDropdown extends BasePageObject {

    @FindBy(className = "wds-notifications")
    private WebElement notificationsBell;

    @Getter
    private Notifications notifications = new Notifications();

    public NotificationsDropdown expand() {
        //NEEDTOCHECK
        wait.forElementVisibleW(notificationsBell);
        hover(notificationsBell);
        //NEEDTOCHECK
        wait.forElementVisibleW(notifications.getNotificationsList());
        return this;
    }

    public NotificationsDropdown collapse() {
        moveAway(notificationsBell);
        //NEEDTOCHECK
        wait.forElementNotVisible(notifications.getNotificationsList());
        return this;
    }
}

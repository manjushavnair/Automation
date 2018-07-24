package com.automation.ui.connected.pageobjectsfactory.pageobject.notifications;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Builder;

@Builder
public class Notification extends BasePageObject {

    private NotificationType type;
    private String actor;
    private String contentObject;

    private String getFormat() {
        return type.getFormat();
    }

    public String getContent() {
        return type == NotificationType.REPLY_UPVOTE ?
                String.format(getFormat(), actor) :
                String.format(getFormat(), actor, contentObject);
    }
}

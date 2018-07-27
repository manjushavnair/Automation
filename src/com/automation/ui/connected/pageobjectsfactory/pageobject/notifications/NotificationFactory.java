package com.automation.ui.connected.pageobjectsfactory.pageobject.notifications;

import com.automation.ui.base.common.core.helpers.User;

public final class NotificationFactory {

    private NotificationFactory() {
        // no-op
    }



    private static Notification getPostReplyNotfication(String user, String postContent) {
        return Notification.builder()
                .actor(user)
                .contentObject(postContent)
                .type(NotificationType.POST_REPLY)
                .build();
    }



    public static Notification getReplyUpvoteNotification() {
        return Notification.builder()
                .actor("1 user")
                .type(NotificationType.REPLY_UPVOTE)
                .build();
    }


}

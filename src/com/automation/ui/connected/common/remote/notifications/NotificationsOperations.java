package com.automation.ui.connected.common.remote.notifications;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.Utils;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "using")
public class NotificationsOperations {

    private static final String NOTIFICATIONS_SERVICE = "notifications/";
    private final User user;

    public static String service(String url) {
        return Utils.buildServicesUrl() + NOTIFICATIONS_SERVICE + url;
    }

    public void markAllAsRead() {
        new MarkAsRead(user).execute();
    }

}

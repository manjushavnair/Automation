package com.automation.ui.connected.elements.mercury.pages.discussions;

import com.automation.ui.connected.elements.mercury.components.discussions.common.ErrorMessages;
import com.automation.ui.connected.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import lombok.Getter;
import org.joda.time.DateTime;

public class ReportedPostsAndRepliesPage extends PageWithPosts {

    private static final String PATH = "/d/reported";
    @Getter(lazy = true)
    private final ErrorMessages errorMessages = new ErrorMessages();

    @Override
    public ReportedPostsAndRepliesPage open() {
        driver.get(getUrlWithCacheBuster(urlBuilder.getUrl() + PATH, "cb=" + DateTime.now().getMillis() + "&AbTest.DISCUSSIONS_LIGHTWEIGHT_CONTRIBUTION_MENU=OLD_1"));
        waitForEmberLoad();
        return this;
    }

    @Override
    public SignInToFollowModalDialog getSignInDialog() {
        throw new UnsupportedOperationException("Reported posts page not reachable for unauthorized users");
    }
}

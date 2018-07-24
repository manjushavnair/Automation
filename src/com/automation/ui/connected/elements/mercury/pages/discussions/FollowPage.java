package com.automation.ui.connected.elements.mercury.pages.discussions;

import com.automation.ui.connected.elements.mercury.components.discussions.common.NoFollowedPostsMessage;
import com.automation.ui.connected.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import lombok.Getter;
import org.joda.time.DateTime;

public class FollowPage extends PageWithPosts {

    private static final String PATH = "/d/follow";

    @Getter(lazy = true)
    private final NoFollowedPostsMessage noFollowedPostsMessage = new NoFollowedPostsMessage();

    @Override
    public FollowPage open() {
        final FollowPage page = new FollowPage();
        page.getUrl(getUrlWithCacheBuster(page.urlBuilder.getUrl() + PATH, "cb=" + DateTime.now().getMillis() + "&AbTest.DISCUSSIONS_LIGHTWEIGHT_CONTRIBUTION_MENU=OLD_1"));
        page.waitForEmberLoad();
        return page;
    }

    @Override
    public SignInToFollowModalDialog getSignInDialog() {
        throw new UnsupportedOperationException("FollowPage not reachable for unauthorized users");
    }
}

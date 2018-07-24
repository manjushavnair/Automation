package com.automation.ui.connected.common.remote.discussions;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.PutRemoteOperation;
import com.automation.ui.connected.common.remote.discussions.context.ThreadContext;

public class DeletePost {

    private static final String DELETE_POST_URL_SUFFIX = "%s/threads/%s/delete";

    private final PutRemoteOperation remoteOperation;

    public DeletePost(User user) {
        this.remoteOperation = new PutRemoteOperation(user);
    }

    public void execute(final ThreadContext context) {
        final String url = buildUrl(context);
        remoteOperation.execute(url);
    }

    private String buildUrl(final ThreadContext context) {
        return DiscussionsClient
                .service(String.format(DELETE_POST_URL_SUFFIX, context.getSiteId(), context.getThreadId()));
    }
}

package com.automation.ui.connected.common.remote.discussions;


import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.PostRemoteOperation;
import com.automation.ui.connected.common.remote.discussions.context.UpvoteContext;
import com.google.common.collect.ImmutableMap;
import org.json.JSONObject;

public class UpvotePost {
    public static final String UPVOTE_POST_SUFFIX = "%s/votes/post/%s";

    private final PostRemoteOperation remoteOperation;

    UpvotePost(User user) {
        remoteOperation = new PostRemoteOperation(user);
    }

    public void execute(final UpvoteContext context) {
        JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
                .put("siteId", context.getSiteId())
                .put("postId", context.getPostId())
                .build());

        remoteOperation.execute(buildUrl(context), jsonObject);
    }

    private String buildUrl(final UpvoteContext context) {
        return DiscussionsClient
                .service(String.format(UPVOTE_POST_SUFFIX, context.getSiteId(), context.getPostId()));
    }
}

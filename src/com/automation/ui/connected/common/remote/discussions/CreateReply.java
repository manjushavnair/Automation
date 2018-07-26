package com.automation.ui.connected.common.remote.discussions;


import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.PostRemoteOperation;
import com.automation.ui.base.common.remote.operations.json.JsonToReplyPostEntityMapper;
import com.automation.ui.connected.common.remote.discussions.context.CreateReplyContext;
import com.automation.ui.connected.elements.mercury.components.discussions.common.ReplyEntityData;
import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;

public class CreateReply {

    public static final String CREATE_REPLY_URL_SUFFIX = "%s/posts";

    private final PostRemoteOperation remoteOperation;

    CreateReply(User user) {
        remoteOperation = new PostRemoteOperation(user);
    }

    public ReplyEntityData execute(final CreateReplyContext context) {
        JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
                .put("siteId", context.getSiteId())
                .put("body", context.getBody())
                .put("title", "")
                .put("threadId", context.getThreadId())
                .put("creatorId", remoteOperation.getUser().getUserId())
                .build());

        final String response = remoteOperation.execute(buildUrl(context), jsonObject);
        return new JsonToReplyPostEntityMapper(JsonPath.parse(response)).toData();
    }

    private String buildUrl(final CreateReplyContext context) {
        return DiscussionsClient
                .service(String.format(CREATE_REPLY_URL_SUFFIX, context.getSiteId()));
    }
}
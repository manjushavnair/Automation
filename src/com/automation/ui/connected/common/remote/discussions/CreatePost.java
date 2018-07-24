package com.automation.ui.connected.common.remote.discussions;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.PostRemoteOperation;
import com.automation.ui.base.common.remote.operations.json.JsonToPostEntityMapper;
import com.automation.ui.connected.common.remote.discussions.context.CreatePostContext;
import com.automation.ui.connected.elements.mercury.components.discussions.common.PostEntity;
import com.google.common.collect.ImmutableMap;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;

public class CreatePost {

    public static final String CREATE_POST_URL_SUFFIX = "%s/forums/%s/threads";

    private final PostRemoteOperation remoteOperation;

    CreatePost(User user) {
        remoteOperation = new PostRemoteOperation(user);
    }

    public PostEntity.Data execute(final CreatePostContext context) {
        JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
                .put("siteId", context.getSiteId())
                .put("title", context.getTitle())
                .put("body", context.getDescription())
                .put("creatorId", remoteOperation.getUser().getUserId())
                .build());

        final String response = remoteOperation.execute(buildUrl(context), jsonObject);
        return new JsonToPostEntityMapper(JsonPath.parse(response)).toData();
    }

    private String buildUrl(final CreatePostContext context) {
        return DiscussionsClient
                .service(String.format(CREATE_POST_URL_SUFFIX, context.getSiteId(), context.getCategoryId()));
    }
}

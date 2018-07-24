package com.automation.ui.connected.common.remote.discussions;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.remote.operations.http.PutRemoteOperation;
import com.automation.ui.connected.common.remote.discussions.context.ModeratePostContext;
import com.google.common.collect.ImmutableMap;
import org.json.JSONObject;

abstract class ModeratePost {

    private final PutRemoteOperation remoteOperation;

    ModeratePost(User user) {
        this.remoteOperation = new PutRemoteOperation(user);
    }

    public void execute(final ModeratePostContext context) {
        JSONObject jsonObject = new JSONObject(ImmutableMap.builder()
                .put("value", 1)
                .build());

        final String url = buildUrl(context);
        remoteOperation.execute(url, jsonObject);
    }

    protected abstract String buildUrl(final ModeratePostContext context);
}

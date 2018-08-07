package com.automation.ui.base.common.remote.operations.http;

import com.automation.ui.base.common.auth.User;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;


public class PostRemoteOperation extends BaseRemoteOperation {

    public PostRemoteOperation(User user) {
        super(user);
    }

    public String execute(final String url, final JSONObject jsonObject) {
        return super.execute(new HttpPost(url), jsonObject);
    }
}

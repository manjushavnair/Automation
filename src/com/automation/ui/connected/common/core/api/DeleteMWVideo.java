package com.automation.ui.connected.common.core.api;

import com.automation.ui.base.common.core.api.ApiCall;
import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.core.url.UrlBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class DeleteMWVideo extends ApiCall {

    private String videoTitle;

    public DeleteMWVideo(String videoTitile) {
        this.videoTitle = videoTitile;
    }

    @Override
    protected String getURL() {
        return UrlBuilder.createUrlBuilder().getUrl().replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX)
                + "/wikia.php?controller=VideoHandler&method=removeVideo&format=json";
    }

    @Override
    protected User getUser() {
        return User.SUS_CHAT_STAFF2;
    }

    @Override
    protected ArrayList<BasicNameValuePair> getParams() {
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("title", videoTitle));
        return params;
    }

    @Override
    protected String getUserName() {
        return null;
    }
}

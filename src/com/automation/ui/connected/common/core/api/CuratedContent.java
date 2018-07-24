package com.automation.ui.connected.common.core.api;

import com.automation.ui.base.common.core.api.ApiCall;
import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.core.url.UrlBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class CuratedContent extends ApiCall {

    public CuratedContent() {
    }

    @Override
    protected String getURL() {
        return UrlBuilder.createUrlBuilder().getUrl().replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX)
                + "/wikia.php?controller=CuratedContent&method=setCuratedContentData";
    }

    @Override
    protected User getUser() {
        return User.STAFF;
    }

    @Override
    protected ArrayList<BasicNameValuePair> getParams() {
        return null;
    }

    @Override
    protected String getUserName() {
        return null;
    }


    /**
     * Clear Curated Content of current wiki Sets empty array as value of wgWikiaCuratedContent
     * wikifactory variable.
     * <p>
     * Add to test body: new CuratedContent().clear();
     */
    public void clear() {
        call();
    }
}

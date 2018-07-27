package com.automation.ui.connected.common.core.url;

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.base.common.core.url.BaseUrlBuilder;

public class FandomUrlBuilder extends BaseUrlBuilder {

    private static final String FANDOM_HOSTNAME = "connected.wikia.com";
    private static final String ARTICLE_PATH = "articles";
    private static final String TOPICS_PATH = "topics";

    public FandomUrlBuilder() {
        super(Configuration.getEnv());
    }

    public String getFandomUrl() {
        return getFandomUrl(envType);
    }

    public String getFandomUrl(EnvType envType) {
        String hostname = FANDOM_HOSTNAME;
        if (!envType.equals(EnvType.PROD)) {
            hostname = env + "." + hostname;
        }

        return HTTP_PREFIX + hostname + "/";
    }

    public String getFandomPageUrl(String path) {
        return addPathToUrl(getFandomUrl(), path);
    }

    public String getUrlForFandomArticlePage(String pageTitle) {
        return getFandomUrl() + ARTICLE_PATH + "/" + pageTitle;
    }

    public String getUrlForFandomTopic(String topic) {
        return getFandomUrl() + TOPICS_PATH + "/" + topic;
    }

    public String getUrlForFandomVideoPage(String videoPage) {
        return getFandomUrl() + videoPage;
    }
}

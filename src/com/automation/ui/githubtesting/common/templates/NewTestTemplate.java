package com.automation.ui.githubtesting.common.templates;

import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.templates.core.CoreTestTemplate;
import org.apache.log4j.Logger;

public abstract class NewTestTemplate extends CoreTestTemplate {


    private static Logger logger = Logger.getLogger(com.automation.ui.githubtesting.common.templates.NewTestTemplate.class);
    protected com.automation.ui.connected.common.core.url.SiteUrlBuilder siteUrlBuilder;

    protected void prepareURLs() {
        urlBuilder = UrlBuilder.createUrlBuilder();
        siteUrlBuilder = new com.automation.ui.connected.common.core.url.SiteUrlBuilder();
        siteURL = urlBuilder.getUrl();
        siteCorporateURL = urlBuilder.getSiteGlobalURL();
        getDataReaders();


    }
}

package com.automation.ui.connected.common.templates;

import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.templates.core.CoreTestTemplate;
import com.automation.ui.connected.common.core.url.SiteUrlBuilder;
import org.apache.log4j.Logger;

public abstract class NewTestTemplate extends CoreTestTemplate {

    private static Logger logger = Logger.getLogger(NewTestTemplate.class);
    protected SiteUrlBuilder siteUrlBuilder;

    protected void prepareURLs() {
        urlBuilder = UrlBuilder.createUrlBuilder();
        siteUrlBuilder = new SiteUrlBuilder();
        siteURL = urlBuilder.getUrl();
        siteCorporateURL = urlBuilder.getSiteGlobalURL();
        getDataReaders();

        //siteCorpSetupURL = UrlBuilder.createUrlBuilderForSiteAndLang("corp", Configuration.DEFAULT_LANGUAGE).getUrl();
        //logger.info("prepareURLs siteCorporateURL : " + siteCorporateURL + " siteURL" +siteURL  );

    }
}

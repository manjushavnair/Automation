package com.automation.ui.connected.common.templates;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.templates.core.CoreTestTemplate;
import com.automation.ui.connected.common.core.url.FandomUrlBuilder;

public class NewTestTemplate extends CoreTestTemplate {

    protected FandomUrlBuilder fandomUrlBuilder;
 /* protected UrlBuilder urlBuilder;

  protected String wikiURL;
  protected String wikiCorporateURL;
  protected String wikiCorpSetupURL;
  */

    protected void loadFirstPage() {
        driver.get(urlBuilder.getUrlForPage(URLsContent.SPECIAL_VERSION) + "?noexternals=1");
    }

    protected void prepareURLs() {
        urlBuilder = UrlBuilder.createUrlBuilder();
        fandomUrlBuilder = new FandomUrlBuilder();
        siteURL = urlBuilder.getUrl();
        siteCorporateURL = urlBuilder.getSiteGlobalURL();
        siteCorpSetupURL = UrlBuilder.createUrlBuilderForSiteAndLang("corp", Configuration.DEFAULT_LANGUAGE).getUrl();
    }
}

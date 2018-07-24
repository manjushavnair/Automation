package com.automation.ui.connected.common.templates.search;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.properties.Credentials;
import com.automation.ui.connected.common.templates.NewTestTemplate;

public class IntraWiki extends NewTestTemplate {

    protected static final String SEARCH_SUGGESTION_PHRASE = "Gon";
    protected static final String SEARCH_ARTICLE = "Gonzo";


    protected Credentials credentials = Configuration.getCredentials();
    protected String testedWiki;
    protected String communityWiki;
    protected String searchSuggestionsWiki;

    public IntraWiki() {
        testedWiki = UrlBuilder.createUrlBuilderForSite(URLsContent.MUPPET_WIKI).getUrl();
        communityWiki = urlBuilder.createUrlBuilderForSiteAndLang(URLsContent.COMMUNITY_WIKI, "en").getUrl();
        searchSuggestionsWiki = UrlBuilder.createUrlBuilderForSiteAndLang(URLsContent.COMMUNITY_COUNCIL_WIKI, "en").getUrl();
    }
}

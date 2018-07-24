package com.automation.ui.base.common.core.url;

import com.automation.ui.base.common.core.configuration.Configuration;

public class Page {
    private String siteName;
    private String sitePath;
    private String siteLanguage;
    private UrlBuilder urlBuilder;

    public Page(String siteName, String language, String sitePath) {
        this.siteName = siteName;
        this.siteLanguage = language;
        this.sitePath = sitePath;
        this.urlBuilder = UrlBuilder.createUrlBuilderForSiteAndLang(this.siteName, this.siteLanguage);
    }

    public Page(String siteName, String sitePath) {
        this.siteName = siteName;
        this.siteLanguage = Configuration.getSiteLanguage();
        this.sitePath = sitePath;
        this.urlBuilder = UrlBuilder.createUrlBuilderForSiteAndLang(this.siteName, this.siteLanguage);
    }

    public Page(String siteName) {
        this.siteName = siteName;
        this.siteLanguage = Configuration.getSiteLanguage();
        this.urlBuilder = UrlBuilder.createUrlBuilderForSiteAndLang(this.siteName, this.siteLanguage);
    }

    public String getSiteName() {
        return siteName;
    }

    public String getUrl() {
        if (this.sitePath == null) {
            return urlBuilder.getUrl(false);
        } else {
            return urlBuilder.getUrlForPage(this.sitePath);
        }
    }

    public String getUrl(String queryParam) {
        return urlBuilder.appendQueryStringToURL(getUrl(), queryParam);
    }

    public String getUrl(String[] queryParams) {
        String url = getUrl();

        for (String param : queryParams) {
            url = urlBuilder.appendQueryStringToURL(url, param);
        }

        return url;
    }
}

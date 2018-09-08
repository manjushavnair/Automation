package com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav;

import com.automation.ui.githubtesting.pageobjectfactory.pageobject.base.SiteBasePageObject;


public class NavigatePage extends SiteBasePageObject {

    public NavigatePage toPage(String pageName) {
        driver.get(urlBuilder.getUrlForPage(pageName) + urlBuilder.getCacheBusterQuery(pageName));

        return this;
    }

    public NavigatePage toPageByPath(String path) {
        String query = urlBuilder.getCacheBusterQuery(path);

        driver.get(urlBuilder.getUrl() + path + query);

        return this;
    }

    public NavigatePage toPageByPath(String path, String fragmentIdentifier) {
        String query = urlBuilder.getCacheBusterQuery(path);
        fragmentIdentifier = "#" + fragmentIdentifier;

        driver.get(urlBuilder.getUrlForPath(path) + query + fragmentIdentifier);

        return this;
    }

    public NavigatePage toPageByPath(String path, String[] queryParams) {
        String query = urlBuilder.getQueryParams(path, queryParams);

        driver.get(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPath(path), query));

        return this;
    }

    public NavigatePage toPageByPath(String host, String path, String[] queryParams) {
        String query = urlBuilder.getQueryParams(path, queryParams);

        driver.get(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPath(path), query));

        return this;
    }

    public NavigatePage toUrl(String url) {
        driver.get(url);
        return this;
    }
}

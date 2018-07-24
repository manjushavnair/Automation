package com.automation.ui.connected.common.templates.fandom;

import com.automation.ui.base.common.templates.core.CoreTestTemplate;
import com.automation.ui.connected.common.core.url.FandomUrlBuilder;

public class FandomTestTemplate extends CoreTestTemplate {

    protected FandomUrlBuilder urlBuilder = new FandomUrlBuilder();
    private String mainURL = "http://www.clayhut.co.in/";

    @Override
    protected void prepareURLs() {

    }

    @Override
    protected void loadFirstPage() {
        driver.navigate().to(mainURL);
    }
}

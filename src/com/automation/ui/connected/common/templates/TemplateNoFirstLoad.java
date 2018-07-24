package com.automation.ui.connected.common.templates;

import com.automation.ui.base.common.core.element.JavascriptActions;


public class TemplateNoFirstLoad extends NewTestTemplate {

    @Override
    protected void loadFirstPage() {
    }

   /* protected AdsBaseObject openPageWithVideoInLocalStorage(Page page, VideoAd videoAd) {
        final AdsBaseObject ads = new AdsBaseObject(driver, UrlBuilder.createUrlBuilderForSite("project43").getUrl());
        putVASTToLocalStorage(videoAd.getVastXML());
        ads.getUrl(page);
        return ads;
    }

    protected AdsBaseObject openPageWithVideoInLocalStorage(Page page) {
        return openPageWithVideoInLocalStorage(page, VuapVideos.DEFAULT);
    }*/

    private void putVASTToLocalStorage(String vast) {
        JavascriptActions runScript = new JavascriptActions(driver);
        runScript.execute(String.format("localStorage.setItem('porvata_vast', '%s');", vast));
    }
}

package com.automation.ui.connected.elements.connected.pages;

import com.automation.ui.connected.elements.connected.SitePage;

public class HomePage extends SitePage<HomePage> {



    @Override
    public HomePage open() {
        getUrl(SITE_URL);

        return this;
    }

    /**
     * Only use this for read-only b/c it touches production
     * Good for testing CORS
     *
     * @return HomePage
     */
    public HomePage openWWW() {
        getUrl(WWW_SITE_URL);

        return this;
    }
}
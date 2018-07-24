package com.automation.ui.base.common.core.drivers.browsers;

import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.drivers.Browser;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import com.automation.ui.base.common.logging.Log;

public class DefaultBrowser extends BrowserAbstract {

    private BrowserAbstract browserClass;

    DefaultBrowser() {
        try {
            browserClass = Browser.lookup(Configuration.getBrowser()).getBrowserClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            Log.logError("Could not initialize the browser", e);
        }
    }

    @Override
    public void setOptions() {
        browserClass.setOptions();
    }

    @Override
    public UIWebDriver create() {
        return browserClass.create();
    }

    @Override
    public void addExtension(String extensionName) {
        browserClass.addExtension(extensionName);
    }
}

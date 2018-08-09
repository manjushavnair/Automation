package com.automation.ui.base.common.core.drivers.browsers;

import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitBrowser extends BrowserAbstract {

    @Override
    public void setOptions() {
        // Here you should put options to set before browser instance creation
    }

    @Override
    public UIWebDriver create() {
        return new UIWebDriver(new HtmlUnitDriver(caps), false);
    }

    @Override
    public void addExtension(String extensionName) {
        // No extensions are applied to HtmlUnit browser
    }
}

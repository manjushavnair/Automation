package com.automation.ui.base.common.core.drivers;

import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.drivers.browsers.*;
import com.automation.ui.base.common.logging.Log;
import org.apache.log4j.Logger;

public enum Browser {
    CHROME(ChromeBrowser.class, "CHROME"),
    FIREFOX(FirefoxBrowser.class, "FF"),
    CHROME_MOBILE(ChromeBrowser.class, "CHROMEMOBILEMERCURY"),
    HTMLUNIT(HtmlUnitBrowser.class, "HTMLUNIT"),
    GHOST(GhostBrowser.class, "GHOST"),
    CHROME_ANDROID(AndroidBrowser.class, "ANDROID"),
    IE(IEBrowser.class, "IE"),
    DEFAULT(DefaultBrowser.class, "");

    private static Logger logger = Logger.getLogger(Browser.class);


    private Class<? extends BrowserAbstract> browserClass;
    private String name;

    Browser(Class<? extends BrowserAbstract> browserClass, String name) {
        this.name = name;
        this.browserClass = browserClass;
    }

    public static Browser lookup(String browserName) {
        for (Browser name : Browser.values()) {
            if (name.getName().equalsIgnoreCase(browserName)) {
                return name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public UIWebDriver getInstance() {
        try {
            return browserClass.newInstance().getInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.info("Could not initialize the browser");
            Log.logError("Could not initialize the browser", e);
        }
        return null;
    }

    public Class<? extends BrowserAbstract> getBrowserClass() {
        return browserClass;
    }
}

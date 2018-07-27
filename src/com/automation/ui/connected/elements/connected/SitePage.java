package com.automation.ui.connected.elements.connected;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;

public abstract class SitePage<T> extends BasePageObject {

    public static final String SITE_URL = "http://sandbox-qa.connected.com/";
    public static final String WWW_SITE_URL = "http://www.sandbox-qa.connected.com/";

    public abstract T open();
}

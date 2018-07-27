package com.automation.ui.connected.elements.connected;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;

public abstract class FandomPage<T> extends BasePageObject {

    public static final String SITE_URL = "http://sandbox-qa.connected.wikia.com/";
    public static final String WWW_SITE_URL = "http://www.clayhut.com.com/";

    public abstract T open();
}

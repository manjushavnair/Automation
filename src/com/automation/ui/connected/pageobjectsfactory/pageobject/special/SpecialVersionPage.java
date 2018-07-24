package com.automation.ui.connected.pageobjectsfactory.pageobject.special;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;

public class SpecialVersionPage extends SiteBasePageObject {

    public SpecialVersionPage() {
        super();
    }

    public SpecialVersionPage open() {
        driver.get(urlBuilder.getUrlForPage(URLsContent.SPECIAL_VERSION));

        return this;
    }
}

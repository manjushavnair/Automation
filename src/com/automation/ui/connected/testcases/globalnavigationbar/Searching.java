package com.automation.ui.connected.testcases.globalnavigationbar;


import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.core.annotations.Execute;
import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.connected.common.templates.NewTestTemplate;
import com.automation.ui.connected.pageobjectsfactory.pageobject.HomePage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = {"globalnavigationbar", "globalnavigationbarSearching"})
public class Searching extends NewTestTemplate {

    @DataProvider
    public Object[][] getDataForGlobalSearch() {
        return new Object[][]{
                {"muppet", "en", "kermit", "Special:Search"},
                {"gta", "de", "san fierro", "Spezial:Suche"},
                {"pad", "zh", "pad", "Special:%E6%90%9C%E7%B4%A2"}
        };
    }




}

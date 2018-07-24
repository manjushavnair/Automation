package com.automation.ui.connected.pageobjectsfactory.pageobject.wikipage;

import com.automation.ui.base.common.core.Assertion;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;

public class SiteHistoryPageObject extends SiteBasePageObject {

    private String getFirstCssRevision() {
        //NEEDTOCHECK
        wait.forElementVisibleW(cssEditSummary);
        String summary = cssEditSummary.getText();
        Log.log("cssEditSummary",
                "the following edit summary was get from Wikia.css: " + summary, true);
        return summary;
    }

    public void verifyLatestEditSummary(String text) {
        String editSummary = getFirstCssRevision();
        editSummary = editSummary.substring(1, editSummary.length() - 1);
        Assertion.assertEquals(editSummary, text);
    }
}

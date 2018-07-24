package com.automation.ui.connected.pageobjectsfactory.pageobject.diffpage;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiffPagePageObject extends BasePageObject {

    @FindBy(css = "#mw-content-text table.diff")
    private WebElement diffTable;

    public void verifyDiffTablePresent() {
        //NEEDTOCHECK
        wait.forElementVisibleW(diffTable);
        Log.log("Verify diff table", "diff table is visible", true);
    }

    public boolean isDiffTableVisible() {
        return diffTable.isDisplayed();
    }

}

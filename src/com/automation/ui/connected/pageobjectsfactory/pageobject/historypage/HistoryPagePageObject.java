package com.automation.ui.connected.pageobjectsfactory.pageobject.historypage;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoryPagePageObject extends BasePageObject {

    @FindBy(css = ".historysubmit")
    private List<WebElement> historySubmit;
    @FindBy(css = ".WikiaPageHeaderDiffHistory h1 strong")
    private WebElement diffHeader;

    public DiffPagePageObject goToDiffPageFromHistoryPage() {
        historySubmit.get(0).click();
        //NEEDTOCHECK
        wait.forElementVisibleW(diffHeader);
        wait.forTextInElement(diffHeader, "Changes");
        return new DiffPagePageObject();
    }
}

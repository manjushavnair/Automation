package com.automation.ui.connected.pageobjectsfactory.pageobject.forumpageobject;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForumHistoryPageObject extends SiteBasePageObject {

    @FindBy(css = "#WallThreadHistory")
    private WebElement threadHistoryTable;
    @FindBy(css = "#WallThreadHistory tr:nth-child(1) td:nth-child(3)")
    private WebElement creatorActionCell;

    public ForumHistoryPageObject(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    public void verifyImportandPageElements() {
        //NEEDTOCHECK
        wait.forElementVisibleW(threadHistoryTable);
        wait.forTextInElement(creatorActionCell, "created this thread");
        Log
                .log("verifyImportandPageElements", "thread history page basic content verified", true);
    }
}

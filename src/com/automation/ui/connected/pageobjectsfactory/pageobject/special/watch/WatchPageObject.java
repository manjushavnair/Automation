/**
 *
 */
package com.automation.ui.connected.pageobjectsfactory.pageobject.special.watch;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchPageObject extends BasePageObject {

    @FindBy(css = "[value=OK]")
    private WebElement followUnfollowConfirmation;

    public WatchPageObject() {
        super();
    }

    public void confirmWatchUnwatch() {
        jsActions.scrollElementIntoViewPort(followUnfollowConfirmation);
        followUnfollowConfirmation.click();
        Log.log("confirmWatchUnwatch", "follow/unfollow confirmation button clicked",
                true);
    }
}

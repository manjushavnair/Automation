package com.automation.ui.connected.pageobjectsfactory.pageobject.auth;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookAuthContext extends BasePageObject {

    @FindBy(css = ".signup-provider-facebook")
    private WebElement connectWithFacebookButton;



    protected boolean isConnectWithFacebookButtonVisible() {
        //NEEDTOCHECK
        return wait.forElementVisibleW(connectWithFacebookButton).isDisplayed();
    }

}

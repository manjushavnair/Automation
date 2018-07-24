package com.automation.ui.connected.pageobjectsfactory.pageobject.auth;

import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormError extends BasePageObject {

    @FindBy(css = "form small.error")
    private WebElement error;

    public String getError() {
        //NEEDTOCHECK
        return wait.forElementVisibleW(error).getText();
    }
}

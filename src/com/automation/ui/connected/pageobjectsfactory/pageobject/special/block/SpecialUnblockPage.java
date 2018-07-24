package com.automation.ui.connected.pageobjectsfactory.pageobject.special.block;

import com.automation.ui.base.common.logging.Log;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialUnblockPage extends SiteBasePageObject {
    public static final String SPECIAL_UNBLOCK_PATH = "Special:Unblock";

    @FindBy(css = "#mw-input-wpTarget")
    private WebElement userNameField;
    @FindBy(css = ".mw-htmlform-submit")
    private WebElement submitButton;
    @FindBy(xpath = "//h1[contains(text(), 'Unblock')]")
    private WebElement unblockedUserHead;

    public SpecialUnblockPage open() {
        getUrl(urlBuilder.getUrlForPath(SPECIAL_UNBLOCK_PATH));
        Log.log("openSpecialUnblockPage", "special unblock page opened", true);

        return this;
    }

    private void typeInUserName(String userName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(userNameField);
        userNameField.sendKeys(userName);
        Log.log("typeInUserName", userName + "typed into username field", true);
    }

    private void clickSubmitButton() {
        //NEEDTOCHECK
        wait.forElementVisibleW(submitButton);
        scrollAndClick(submitButton);
        Log.log("clickSubmitButton", "submit button clicked", true);
    }

    public void unblockUser(String userName) {
        typeInUserName(userName);
        clickSubmitButton();
    }

    public void verifyUnblockMessage(String userName) {
        //NEEDTOCHECK
        wait.forElementVisibleW(unblockedUserHead);
        wait.forElementVisible(By.xpath("//div[@id='mw-content-text']//a[@href='/wiki/User:" + userName
                + "' and contains(text(), '" + userName + "')]"));
        wait.forElementVisible(
                By.xpath("//div[@id='mw-content-text']//p[contains(text(), 'has been unblocked')]"));
        Log.log("verifyUnblockMessage", "unblock user messages verified", true, driver);
    }
}

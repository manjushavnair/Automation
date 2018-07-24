package com.automation.ui.connected.elements.mercury.old;

import com.automation.ui.base.common.core.element.Wait;
import com.automation.ui.base.common.core.url.UrlBuilder;
import com.automation.ui.base.common.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class JoinPageObject {

    @FindBy(css = ".register-message:last-child")
    private WebElement joinTodayMessage;

    @FindBy(css = ".button.signup-provider-email")
    private WebElement registerWithEmailButton;

    @FindBy(css = "a.sign-in")
    private WebElement signInLink;

    private WebDriver driver;
    private Wait wait;
    private UrlBuilder urlBuilder = UrlBuilder.createUrlBuilder();

    public JoinPageObject(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);

        PageFactory.initElements(driver, this);
    }

    public JoinPageObject get() {
        String redirectParameter = "";
        try {
            redirectParameter =
                    URLEncoder.encode(urlBuilder.getUrl(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            Log.log("encoding", "problem occured during URL encoding", false);
        }
        driver.get(urlBuilder.getUrl() + "/join" + "?redirect="
                + redirectParameter);
        return this;
    }

    public String getJoinTodayText() {
        return joinTodayMessage.getText();
    }

    public void clickRegisterWithEmail() {
        //NEEDTOCHECK
        wait.forElementVisibleW(registerWithEmailButton);
        registerWithEmailButton.click();
    }

    public void clickSignInLink() {
        //NEEDTOCHECK
        wait.forElementVisibleW(signInLink);
        signInLink.click();
    }
}


package com.automation.ui.connected.pageobjectsfactory.pageobject.auth;

import com.automation.ui.base.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.register.AttachedRegisterPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPageContext extends SiteBasePageObject {

    @FindBy(css = ".register-page .header-callout-link")
    private WebElement linkToSignInForm;
    @FindBy(css = ".signin-page .header-callout-link")
    private WebElement linkToSignUpForm;
    @FindBy(css = ".auth-header")
    private WebElement authHeader;
    @FindBy(css = ".second-card .auth-header")
    private WebElement secondCardHeader;

    private FacebookAuthContext fbAuthContext;

    public AuthPageContext() {
        fbAuthContext = new FacebookAuthContext();
    }



    public boolean isConnectWithFacebookButtonVisible() {
        return fbAuthContext.isConnectWithFacebookButtonVisible();
    }

    public AttachedSignInPage navigateToSignIn() {
        waitAndClick(linkToSignInForm);
        return new AttachedSignInPage();
    }

    public RegisterPage navigateToSignUp() {
        waitAndClick(linkToSignUpForm);
        return new AttachedRegisterPage();
    }

    public boolean isHeaderDisplayed() {
        //NEEDTOCHECK
        return wait.forElementVisibleW(authHeader).isDisplayed();
    }

    public boolean confirmationDisplayed(String text) {
        //NEEDTOCHECK
        return wait.forElementVisibleW(secondCardHeader).getText().contains(text);
    }

}

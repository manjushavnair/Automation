package com.automation.ui.connected.pageobjectsfactory.pageobject.auth.signin;

import com.automation.ui.base.common.contentpatterns.URLsContent;
import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.automation.ui.connected.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.FormError;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedSignInPage extends BasePageObject implements SignInPage {

    @FindBy(css = ".forgotten-password")
    private WebElement forgottenPasswordLink;
    @FindBy(id = "loginUsername")
    private WebElement usernameField;
    @FindBy(id = "loginPassword")
    private WebElement passwordField;
    @FindBy(id = "loginSubmit")
    private WebElement signInButton;
    @FindBy(css = ".password-toggler")
    private WebElement passwordToggler;

    private AuthPageContext authContext;

    public AttachedSignInPage() {
        authContext = new AuthPageContext();
    }

    public AttachedForgotPasswordPage clickForgotPasswordLink() {
        waitAndClick(forgottenPasswordLink);
        return new AttachedForgotPasswordPage();
    }

    public SignInPage typePassword(String password) {
        fillInput(passwordField, password);
        return this;
    }

    @Override
    public SignInPage typeUsername(String username) {
        fillInput(usernameField, username);
        return this;
    }

    @Override
    public void login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        submit();
    }

    @Override
    public void login(User user) {
        login(user.getUserName(), user.getPassword());

    }

    @Override
    public RegisterPage navigateToRegister() {
        return authContext.navigateToSignUp();
    }

    @Override
    public String getError() {
        return new FormError().getError();
    }

    @Override
    public boolean isPasswordMasked() {
        return "password".equals(passwordField.getAttribute("type"));
    }

    @Override
    public void togglePasswordVisibility() {
        waitAndClick(passwordToggler);
    }

    @Override
    public void submit() {
        waitAndClick(signInButton);
    }

    @Override
    public AttachedSignInPage open() {
        getUrl(getSiteUrl() + URLsContent.USER_LOGIN);
        return this;
    }

    @Override
    public boolean isDisplayed() {
        return authContext.isHeaderDisplayed();
    }

    FacebookSignupModalComponentObject clickFacebookSignUp() {
        return authContext.clickFacebookSignUp();
    }

    public boolean isConnectWithFacebookButtonVisible() {
        return authContext.isConnectWithFacebookButtonVisible();
    }

    @Override
    public boolean submitButtonNotClickable() {
        //NEEDTOCHECK
        return !wait.forElementVisibleW(signInButton).isEnabled();
    }
}

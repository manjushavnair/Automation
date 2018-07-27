package com.automation.ui.connected.pageobjectsfactory.pageobject.auth.signin;

import com.automation.ui.base.common.core.helpers.User;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.FormPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.register.RegisterPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.forgotpassword.AttachedForgotPasswordPage;
import com.automation.ui.connected.pageobjectsfactory.pageobject.auth.forgotpassword.DetachedForgotPasswordPage;

public class DetachedSignInPage extends DetachedWindow implements SignInPage {

    private static final String TITLE = "Sign in";
    private AttachedSignInPage signInPage;

    public DetachedSignInPage() {
        signInPage = new AttachedSignInPage();
    }

    public DetachedSignInPage(AttachedSignInPage page) {
        signInPage = page;
    }

    @Override
    public ForgotPasswordPage clickForgotPasswordLink() {
        gainFocus();
        AttachedForgotPasswordPage forgotPassword = signInPage.clickForgotPasswordLink();
        return new DetachedForgotPasswordPage(forgotPassword);
    }

    @Override
    public SignInPage typePassword(String password) {
        gainFocus();
        signInPage.typePassword(password);
        return this;
    }

    @Override
    public SignInPage typeUsername(String username) {
        gainFocus();
        signInPage.typeUsername(username);
        return this;
    }

    @Override
    public void login(String username, String password) {
        gainFocus();
        signInPage.login(username, password);
        loseFocus();
    }

    @Override
    public void login(User user) {
        login(user.getUserName(), user.getPassword());
    }

    @Override
    public RegisterPage navigateToRegister() {
        gainFocus();
        return signInPage.navigateToRegister();
    }

    @Override
    public String getError() {
        gainFocus();
        return signInPage.getError();
    }

    @Override
    public boolean isPasswordMasked() {
        gainFocus();
        return signInPage.isPasswordMasked();
    }

    @Override
    public void togglePasswordVisibility() {
        gainFocus();
        signInPage.togglePasswordVisibility();
    }

    @Override
    public void submit() {
        gainFocus();
        signInPage.submit();
    }

    @Override
    public FormPage open() {
        throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
    }

    @Override
    public boolean isDisplayed() {
        gainFocus();
        return signInPage.isDisplayed();
    }

    @Override
    public boolean submitButtonNotClickable() {
        gainFocus();
        return signInPage.submitButtonNotClickable();
    }



    public boolean isConnectWithFacebookButtonVisible() {
        gainFocus();
        return signInPage.isConnectWithFacebookButtonVisible();
    }

    @Override
    protected String getTitle() {
        return TITLE;
    }
}

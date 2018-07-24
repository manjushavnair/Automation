package com.automation.ui.connected.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.automation.ui.base.pageobjectsfactory.pageobject.auth.DetachedWindow;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.FormPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;

public class DetachedForgotPasswordPage extends DetachedWindow implements ForgotPasswordPage {


    private AttachedForgotPasswordPage forgotPasswordPage;
    public static final String TITLE = "Join Site Today";

    public DetachedForgotPasswordPage(AttachedForgotPasswordPage page) {
        forgotPasswordPage = page;
    }

    public DetachedForgotPasswordPage() {
        forgotPasswordPage = new AttachedForgotPasswordPage();
    }

    @Override
    public String getError() {
        gainFocus();
        return forgotPasswordPage.getError();

    }

    @Override
    public void submit() {
        gainFocus();
        forgotPasswordPage.submit();
        loseFocus();
    }

    @Override
    public FormPage open() {
        throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
    }

    @Override
    public boolean isDisplayed() {
        gainFocus();
        return forgotPasswordPage.isDisplayed();
    }

    @Override
    public boolean submitButtonNotClickable() {
        return forgotPasswordPage.submitButtonNotClickable();
    }

    @Override
    public void requestLinkForUsername(String username) {
        gainFocus();
        forgotPasswordPage.requestLinkForUsername(username);
        if (forgotPasswordPage.isConfirmationDisplayed()) {
            loseFocus();
        }
    }

    @Override
    protected String getTitle() {
        return TITLE;
    }
}

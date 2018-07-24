package com.automation.ui.base.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.automation.ui.base.pageobjectsfactory.pageobject.auth.FormPage;

public interface ForgotPasswordPage extends FormPage {

    void requestLinkForUsername(String username);
}

package com.automation.ui.base.common.auth.forgotpassword;

import com.automation.ui.base.common.auth.FormPage;

public interface ForgotPasswordPage extends FormPage {

    void requestLinkForUsername(String username);
}

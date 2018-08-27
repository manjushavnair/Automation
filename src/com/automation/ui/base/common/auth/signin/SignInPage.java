package com.automation.ui.base.common.auth.signin;

import com.automation.ui.base.common.auth.FormPage;
import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.auth.forgotpassword.ForgotPasswordPage;
import com.automation.ui.base.common.auth.register.RegisterPage;

public interface SignInPage extends FormPage {

    ForgotPasswordPage clickForgotPasswordLink();

    SignInPage typePassword(String password);

    SignInPage typeUsername(String password);

    void login(String username, String password);

    void login(User user);

    RegisterPage navigateToRegister();

    String getError();

    boolean isPasswordMasked();

    void togglePasswordVisibility();

}

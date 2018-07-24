package com.automation.ui.base.pageobjectsfactory.pageobject.auth.register;

import com.automation.ui.base.common.core.helpers.SignUpUser;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.FormPage;
import com.automation.ui.base.pageobjectsfactory.pageobject.auth.signin.SignInPage;

import java.time.LocalDate;

public interface RegisterPage extends FormPage {

    RegisterPage typeEmailAddress(String email);

    RegisterPage typeUsername(String username);

    RegisterPage typePassword(String password);

    RegisterPage typeBirthday(int month, int day, int year);

    SignInPage navigateToSignIn();

    void signUp(String email, String username, String password, LocalDate birthday);

    void signUp(SignUpUser user);

    RegisterPage fillForm(SignUpUser user);

    boolean isPasswordMasked();

    void togglePasswordVisibility();

}
